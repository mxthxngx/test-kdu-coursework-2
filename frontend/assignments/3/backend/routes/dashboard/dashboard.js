const express = require('express');
const router = express.Router();
const dashboardData = require("../../data/dashboardExplore");

let isDataFetched = false; 

router.get("/explore", async (req, res) => {
    try {
        if (!isDataFetched) { 
            const response = await fetch('https://dev-1gyvfva3nqtb0v4.api.raw-labs.com/mock/stocks');
            if (!response.ok) {
                throw new Error(`Failed to fetch stocks (${response.status} ${response.statusText})`);
            }

            const fetchedData = await response.json();
            dashboardData.length = 0;
            dashboardData.push(...fetchedData);
            console.log(dashboardData.length);

            isDataFetched = true; 
        }

        res.json(dashboardData);
    } catch (error) {
        console.error('Error fetching stocks:', error);
        res.status(500).json({ error: 'Internal Server Error' });
    }
});

module.exports = router;
