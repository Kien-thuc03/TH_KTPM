const express = require('express');
const mongoose = require('mongoose');
const cors = require('cors');
require('dotenv').config();

const app = express();

// Middleware
app.use(cors());
app.use(express.json());

// Connect to MongoDB
mongoose.connect(process.env.MONGODB_URI || 'mongodb://localhost:27017/inventory-service', {
    useNewUrlParser: true,
    useUnifiedTopology: true
});

// Routes
app.use('/api/inventory', require('./routes/inventoryRoutes'));

// Error handling middleware
app.use((err, req, res, next) => {
    console.error(err.stack);
    res.status(500).json({ message: 'Something went wrong!' });
});

const PORT = process.env.PORT || 3004;
app.listen(PORT, () => {
    console.log(`Inventory Service is running on port ${PORT}`);
});