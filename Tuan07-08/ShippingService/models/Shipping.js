const mongoose = require('mongoose');

const shippingSchema = new mongoose.Schema({
    orderId: {
        type: String,
        required: true,
        unique: true
    },
    status: {
        type: String,
        enum: ['pending', 'processing', 'in_transit', 'delivered', 'failed'],
        default: 'pending'
    },
    address: {
        street: String,
        city: String,
        state: String,
        zipCode: String,
        country: String
    },
    trackingNumber: {
        type: String,
        unique: true
    },
    carrier: {
        type: String,
        required: true
    },
    estimatedDeliveryDate: Date,
    actualDeliveryDate: Date,
    notes: String,
    createdAt: {
        type: Date,
        default: Date.now
    },
    updatedAt: {
        type: Date,
        default: Date.now
    }
});

module.exports = mongoose.model('Shipping', shippingSchema);