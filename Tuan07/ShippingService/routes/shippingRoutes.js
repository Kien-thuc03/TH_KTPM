const express = require('express');
const router = express.Router();
const Shipping = require('../models/Shipping');

// Create new shipping order
router.post('/', async(req, res) => {
    try {
        const shipping = new Shipping({
            orderId: req.body.orderId,
            address: req.body.address,
            carrier: req.body.carrier,
            estimatedDeliveryDate: req.body.estimatedDeliveryDate,
            trackingNumber: `SHIP${Date.now()}${Math.floor(Math.random() * 1000)}`
        });
        const savedShipping = await shipping.save();
        res.status(201).json(savedShipping);
    } catch (error) {
        res.status(400).json({ message: error.message });
    }
});

// Get shipping status
router.get('/:id', async(req, res) => {
    try {
        const shipping = await Shipping.findById(req.params.id);
        if (!shipping) {
            return res.status(404).json({ message: 'Shipping order not found' });
        }
        res.json(shipping);
    } catch (error) {
        res.status(500).json({ message: error.message });
    }
});

// Update shipping status
router.patch('/:id/status', async(req, res) => {
    try {
        const shipping = await Shipping.findById(req.params.id);
        if (!shipping) {
            return res.status(404).json({ message: 'Shipping order not found' });
        }

        shipping.status = req.body.status;
        shipping.updatedAt = Date.now();

        if (req.body.status === 'delivered') {
            shipping.actualDeliveryDate = Date.now();
        }

        if (req.body.notes) {
            shipping.notes = req.body.notes;
        }

        const updatedShipping = await shipping.save();
        res.json(updatedShipping);
    } catch (error) {
        res.status(500).json({ message: error.message });
    }
});

// Get shipping by order ID
router.get('/order/:orderId', async(req, res) => {
    try {
        const shipping = await Shipping.findOne({ orderId: req.params.orderId });
        if (!shipping) {
            return res.status(404).json({ message: 'Shipping order not found' });
        }
        res.json(shipping);
    } catch (error) {
        res.status(500).json({ message: error.message });
    }
});

module.exports = router;