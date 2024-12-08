const express = require('express');
const router = express.Router();
const transactionData = require('../../data/transactionData');

/**
 * Applies filters to the transactions based on the provided filter values.
 *
 * @param {Array} transactions - the array of transactions to be filtered
 * @param {Object} filterValues - an object containing filter values like searchQuery, startDate, endDate, selectedStockNames, filterPassed, filterFailed
 * @return {Array} - the filtered array of transactions
 */
const applyFilters = (transactions, filterValues) => {
    const { searchQuery, startDate, endDate, selectedStockNames, filterPassed, filterFailed } = filterValues;

    return transactions.filter(transaction => {
        // Stock and symbol match
        const stockMatch = transaction.stockname.toLowerCase().includes(searchQuery.toLowerCase()) || 
                            transaction.symbol.toLowerCase().includes(searchQuery.toLowerCase());

        // Date match
        const transactionDate = new Date(transaction.date);
        const dateMatch = (!startDate || transactionDate >= new Date(startDate)) &&
                            (!endDate || transactionDate <= new Date(endDate));

        // Stock name match
        const stockNameMatch = selectedStockNames.length === 0 || 
                                selectedStockNames.includes(transaction.stockname);

        // Success match
        const successMatch = (!filterPassed || transaction.type === 'Passed') &&
                            (!filterFailed || transaction.type !== 'Passed');

        return stockMatch && dateMatch && stockNameMatch && successMatch;
    });
};

router.post("/applyFilters", (req, res) => {
    const { username, filterValues } = req.body;
    console.log("applying filters", username, filterValues);

    const transactions = transactionData[username] || [];

    const filteredTransactions = applyFilters(transactions, filterValues);

    res.json({ filteredTransactions });
});

router.get("/get/:username", async (req, res) => {
    try {
        const { username } = req.params;

        const response = await fetch('https://kdu-automation.s3.ap-south-1.amazonaws.com/mini-project-apis/portfolio-transactions.json');
        if (!response.ok) {
            throw new Error('Error fetching from API', { cause: response });
        }
        const apiTransactions = await response.json(); 

        if (!transactionData[username]) {
            transactionData[username] = [];
        }

        apiTransactions.forEach(transaction => {
            transactionData[username].push({
                username: username,
                stockname: transaction.stock_name,
                symbol: transaction.stock_symbol,
                price: transaction.transaction_price,
                date: transaction.timestamp,
                type: transaction.status
            });
        });

        transactionData[username].sort((a, b) => new Date(b.date) - new Date(a.date));

        res.status(200).json({ transactionData: transactionData[username] });
    } catch (error) {
        console.error('Error updating transactions:', error);
        res.status(500).json({ error: 'Internal server error' });
    }
});

module.exports = router;
