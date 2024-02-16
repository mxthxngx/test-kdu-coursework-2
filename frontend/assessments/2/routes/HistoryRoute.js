const express = require('express');
const router = express.Router();
const historyData = require('../data/History');
const cors = require('cors');
router.use(cors());
// GET request to retrieve history data
router.get("/", (req, res) => {
    try {
        console.log("Requested");
        res.json(historyData);
    } catch (error) {
        console.error("Error retrieving history data:", error);
        res.status(500).json({ error: "Internal server error" });
    }
});

router.post("/", (req, res) => {
    try {
        const { action, qty,currentPrice } = req.body; // Extract action and qty from request body
        console.log("Received history request - Action:", action, "Qty:", qty);
        
        historyData.push({ action, qty ,currentPrice});
        
        res.json(historyData);

    } catch (error) {
        console.error("Error processing POST request:", error);
        res.status(500).json({ error: "Internal server error" });
    }
});

module.exports = router;
