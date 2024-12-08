const express = require('express');
const router = express.Router();
const userData = require("../../data/userData");
const transactionData = require("../../data/transactionData");

router.post("/maketransaction", (req, res) => {
    console.log("executing post")
    const { amount, stockname, symbol, price, date, username } = req.body;
    console.log("amount: ", amount, "stockname: ", stockname, "symbol: ", symbol, "basePrice: ", price, "date: ", date, "username: ", username)

    const userIndex = userData.findIndex(user => user.username === username);

    if (userIndex === -1) {
        return res.status(404).json({ error: 'User not found' });
    }

    const user = userData[userIndex];
    console.log("amount: ", amount, "stockname: ", stockname, "symbol: ", symbol, "basePrice: ", price, "date: ", date, "username: ", username, "wallet:")
    if (user.walletAmount < amount) {
        if (!transactionData[username]) {
            transactionData[username] = [];
        }

        transactionData[username].push({
            username: username,
            stockname: stockname,
            type: 'Failed',
            symbol: symbol,
            price: price,
            date: date,
        });

        console.error("Transaction failed: Insufficient balance", transactionData[username]);
        return res.status(400).json({ error: 'Insufficient balance' });
    }

    userData[userIndex].walletAmount -= amount;

    if (!transactionData[username]) {
        transactionData[username] = [];
    }

    transactionData[username].push({
        username: username,
        stockname: stockname,
        type: 'Passed',
        symbol: symbol,
        price: price,
        date: date,
        amount: amount
    });

    console.log("Successful transaction", transactionData[username])
    res.json({ status: 'success' });
});

module.exports = router;
