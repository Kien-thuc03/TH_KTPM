const express = require('express');
const router = express.Router();
const Payment = require('../models/Payment');

// Create a new payment
router.post('/', async(req, res) => {
    try {
        const payment = new Payment({
            orderId: req.body.orderId,
            amount: req.body.amount,
            paymentMethod: req.body.paymentMethod,
            transactionId: `TXN${Date.now()}${Math.floor(Math.random() * 1000)}`
        });
        const savedPayment = await payment.save();
        res.status(201).json(savedPayment);
    } catch (error) {
        res.status(400).json({ message: error.message });
    }
});

// Get payment status
router.get('/:id', async(req, res) => {
    try {
        const payment = await Payment.findById(req.params.id);
        if (!payment) {
            return res.status(404).json({ message: 'Payment not found' });
        }
        res.json(payment);
    } catch (error) {
        res.status(500).json({ message: error.message });
    }
});

// Process refund
router.post('/:id/refund', async(req, res) => {
    try {
        const payment = await Payment.findById(req.params.id);
        if (!payment) {
            return res.status(404).json({ message: 'Payment not found' });
        }
        if (payment.status !== 'completed') {
            return res.status(400).json({ message: 'Payment cannot be refunded' });
        }
        payment.status = 'refunded';
        payment.updatedAt = Date.now();
        const updatedPayment = await payment.save();
        res.json(updatedPayment);
    } catch (error) {
        res.status(500).json({ message: error.message });
    }
});

module.exports = router;