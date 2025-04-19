const mongoose = require('mongoose');

const inventorySchema = new mongoose.Schema({
    productId: {
        type: String,
        required: true,
        unique: true
    },
    quantity: {
        type: Number,
        required: true,
        min: 0
    },
    reservedQuantity: {
        type: Number,
        default: 0,
        min: 0
    },
    lastUpdated: {
        type: Date,
        default: Date.now
    },
    lowStockThreshold: {
        type: Number,
        default: 10
    },
    status: {
        type: String,
        enum: ['in_stock', 'low_stock', 'out_of_stock'],
        default: 'in_stock'
    }
});

// Middleware to update status based on quantity
inventorySchema.pre('save', function(next) {
    if (this.quantity <= 0) {
        this.status = 'out_of_stock';
    } else if (this.quantity <= this.lowStockThreshold) {
        this.status = 'low_stock';
    } else {
        this.status = 'in_stock';
    }
    next();
});

module.exports = mongoose.model('Inventory', inventorySchema);