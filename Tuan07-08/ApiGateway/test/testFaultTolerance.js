const axios = require('axios');

const testCircuitBreaker = async() => {
    console.log('Testing Circuit Breaker...');
    try {
        // Gửi nhiều request liên tiếp để kích hoạt circuit breaker
        for (let i = 0; i < 10; i++) {
            try {
                const response = await axios.get('http://localhost:8080/api/customers');
                console.log(`Request ${i + 1}: Success`);
            } catch (error) {
                console.log(`Request ${i + 1}: Failed - ${error.response?.data?.message || error.message}`);
            }
            await new Promise(resolve => setTimeout(resolve, 500));
        }
    } catch (error) {
        console.error('Test failed:', error.message);
    }
};

const testRetry = async() => {
    console.log('\nTesting Retry Mechanism...');
    try {
        const response = await axios.get('http://localhost:8080/api/customers/nonexistent');
        console.log('Response:', response.data);
    } catch (error) {
        console.log('Retry test result:', error.response?.data?.message || error.message);
    }
};

const testRateLimiter = async() => {
    console.log('\nTesting Rate Limiter...');
    try {
        const requests = Array(20).fill().map(() =>
            axios.get('http://localhost:8080/api/customers')
        );
        const results = await Promise.allSettled(requests);

        results.forEach((result, index) => {
            if (result.status === 'fulfilled') {
                console.log(`Request ${index + 1}: Success`);
            } else {
                console.log(`Request ${index + 1}: Failed - ${result.reason.response?.data?.message || result.reason.message}`);
            }
        });
    } catch (error) {
        console.error('Test failed:', error.message);
    }
};

const testTimeLimiter = async() => {
    console.log('\nTesting Time Limiter...');
    try {
        const response = await axios.get('http://localhost:8080/api/orders/slow');
        console.log('Response:', response.data);
    } catch (error) {
        console.log('Time limiter test result:', error.response?.data?.message || error.message);
    }
};

const runAllTests = async() => {
    console.log('Starting fault tolerance tests...');
    await testCircuitBreaker();
    await testRetry();
    await testRateLimiter();
    await testTimeLimiter();
    console.log('\nAll tests completed!');
};

runAllTests();