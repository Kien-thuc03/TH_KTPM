const express = require('express');
const router = express.Router();
const Inventory = require('../models/Inventory');

// Create or update inventory
router.post('/', async(req, res) => {
    try {
        const inventory = await Inventory.findOneAndUpdate({ productId: req.body.productId }, {
            productId: req.body.productId,
            quantity: req.body.quantity,
            lowStockThreshold: req.body.lowStockThreshold,
            lastUpdated: Date.now()
        }, { new: true, upsert: true });
        res.status(201).json(inventory);
    } catch (error) {
        res.status(400).json({ message: error.message });
    }
});

// Get inventory by product ID
router.get('/product/:productId', async(req, res) => {
    try {
        const inventory = await Inventory.findOne({ productId: req.params.productId });
        if (!inventory) {
            return res.status(404).json({ message: 'Inventory not found' });
        }
        res.json(inventory);
    } catch (error) {
        res.status(500).json({ message: error.message });
    }
});

// Update quantity (for order processing)
router.post('/update-quantity', async(req, res) => {
    try {
        const { productId, quantity, operation } = req.body;
        const inventory = await Inventory.findOne({ productId });

        if (!inventory) {
            return res.status(404).json({ message: 'Inventory not found' });
        }

        if (operation === 'decrease') {
            if (inventory.quantity < quantity) {
                return res.status(400).json({ message: 'Insufficient stock' });
            }
            inventory.quantity -= quantity;
        } else if (operation === 'increase') {
            inventory.quantity += quantity;
        }

        inventory.lastUpdated = Date.now();
        const updatedInventory = await inventory.save();
        res.json(updatedInventory);
    } catch (error) {
        res.status(500).json({ message: error.message });
    }
});

// Get low stock items
router.get('/low-stock', async(req, res) => {
    try {
        const lowStockItems = await Inventory.find({
            status: { $in: ['low_stock', 'out_of_stock'] }
        });
        res.json(lowStockItems);
    } catch (error) {
        res.status(500).json({ message: error.message });
    }
});

module.exports = router;