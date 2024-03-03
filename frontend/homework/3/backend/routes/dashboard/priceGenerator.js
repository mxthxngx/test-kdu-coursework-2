const express = require('express');
const router = express.Router();
const dashboardData = require("../../data/dashboardExplore")

router.get("/stock/:symbol", (req, res) => {
    const symbol = req.params.symbol;
    const dashboardItem = dashboardData.find(item => item.stock_symbol === symbol);
    if (!dashboardItem) {
        return res.status(404).json({ error: 'Symbol not found' });
    }
    const randomPrice = generateRandomPrice(dashboardItem.base_price);

    const response = {
        symbol: symbol,
        price: randomPrice
    };

    res.json(response);
});
/**
 * 
 * @param {*} basePrice 
 * random value between 5% up and down of the base price
 */
function generateRandomPrice(basePrice) {
    const deviationPercentage = (Math.random() * 0.1) - 0.05;
    const randomPrice = basePrice * (1 + deviationPercentage);
    return randomPrice.toFixed(2);
}

module.exports = router;
