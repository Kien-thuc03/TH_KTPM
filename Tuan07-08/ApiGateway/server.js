const express = require('express');
const cors = require('cors');
const axios = require('axios');
const rateLimit = require('express-rate-limit');
const CircuitBreaker = require('opossum');
const { createProxyMiddleware } = require('http-proxy-middleware');
const timeout = require('connect-timeout');
require('dotenv').config();

const app = express();

app.use(cors());
app.use(express.json());
app.use(express.urlencoded({ extended: true }));

// Rate limiter - 5 requests per minute
const limiter = rateLimit({
    windowMs: 60 * 1000, // 1 minute
    max: 5 // limit each IP to 5 requests per minute
});
app.use(limiter);

// Circuit Breaker configuration with Open-HalfOpen-Closed states
const breaker = new CircuitBreaker(axios, {
    timeout: 3000, // If our function takes longer than 3 seconds, trigger a failure
    resetTimeout: 30000, // After 30 seconds, try again.
    errorThresholdPercentage: 50, // When 50% of requests fail, trip the circuit
    volumeThreshold: 5, // Need at least 5 requests before checking error percentage
    errorFilter: (err) => {
        return err.code === 'ETIMEDOUT' || err.code === 'ECONNREFUSED';
    }
});

breaker.on('open', () => console.log('Circuit Breaker opened'));
breaker.on('halfOpen', () => console.log('Circuit Breaker half-opened'));
breaker.on('close', () => console.log('Circuit Breaker closed'));

// Retry configuration - 5 retries with 3 second delay
const axiosRetry = require('axios-retry').default;
axiosRetry(axios, {
    retries: 5,
    retryDelay: () => 3000, // 3 seconds delay between retries
    retryCondition: (error) => {
        return axiosRetry.isNetworkOrIdempotentRequestError(error) ||
            error.code === 'ECONNABORTED' ||
            error.code === 'ETIMEDOUT';
    }
});

// Time Limiter configuration
app.use(timeout('2s')); // Default 2s timeout for service A
app.use((req, res, next) => {
    if (!req.timedout) next();
});

// Custom timeout for service B (3s)
const serviceBTimeout = timeout('3s');


// Add request logging
app.use((req, res, next) => {
    console.log(`${new Date().toISOString()} - ${req.method} ${req.originalUrl}`);
    next();
});

// Proxy middleware configurations
const customerServiceProxy = createProxyMiddleware({
    target: 'http://localhost:3001',
    changeOrigin: true,
    ws: true,
    logLevel: 'debug',
    proxyTimeout: 2000, // 2s timeout
    onError: (err, req, res) => {
        console.error('Customer Service Error:', err);
        res.status(500).json({ message: 'Customer Service unavailable' });
    }
});

const productServiceProxy = createProxyMiddleware({
    target: 'http://localhost:3000',
    changeOrigin: true,
    ws: true,
    proxyTimeout: 2000, // 2s timeout
    onError: (err, req, res) => {
        breaker.fire()
            .then(response => res.json(response.data))
            .catch(error => {
                console.error('Product Service Error:', error);
                res.status(503).json({ message: 'Service temporarily unavailable' });
            });
    }
});

const orderServiceProxy = createProxyMiddleware({
    target: 'http://localhost:3002',
    changeOrigin: true,
    ws: true,
    proxyTimeout: 3000, // 3s timeout for service B
    onError: (err, req, res) => {
        breaker.fire()
            .then(response => res.json(response.data))
            .catch(error => {
                console.error('Order Service Error:', error);
                res.status(503).json({ message: 'Service temporarily unavailable' });
            });
    }
});

// Route handlers - remove pathRewrite and simplify
app.use('/api/customers', customerServiceProxy);
app.use('/api/products', productServiceProxy);
app.use('/api/orders', orderServiceProxy);
app.use('/api/payments', createProxyMiddleware({
    target: 'http://localhost:3003',
    changeOrigin: true,
    ws: true
}));
app.use('/api/inventory', createProxyMiddleware({
    target: 'http://localhost:3004',
    changeOrigin: true,
    ws: true
}));
app.use('/api/shipping', createProxyMiddleware({
    target: 'http://localhost:3005',
    changeOrigin: true,
    ws: true
}));

// Add root route
app.get('/', (req, res) => {
    res.json({ message: 'API Gateway is running' });
});

// Health check endpoint with more details
app.get('/health', async(req, res) => {
    const services = {
        gateway: 'OK',
        customers: await checkService('http://localhost:3001/api/customers'),
        products: await checkService('http://localhost:3000/api/products'),
        orders: await checkService('http://localhost:3002/api/orders')
    };
    res.json({ status: services });
});

async function checkService(url) {
    try {
        await axios.get(url, { timeout: 2000 });
        return 'OK';
    } catch (error) {
        return 'Down';
    }
}

const PORT = 8080;
app.listen(PORT, () => {
    console.log(`API Gateway running on port ${PORT}`);
    console.log('Proxying to:');
    console.log('- Customers:', 'http://localhost:3001');
    console.log('- Products:', 'http://localhost:3000');
    console.log('- Orders:', 'http://localhost:3002');
});