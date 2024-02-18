const express = require('express');
const router = express.Router();
const registeredUsers = require("../../data/Users/registeredUsers");
let isLoggedIn = require("../../data/Users/loggedInUsers");

/**
 * Route to validate user credentials and perform login.
 * 
 * @param {Object} req - The request object containing user credentials (username, password)
 * @param {Object} res - The response object for sending HTTP responses
 */
router.post('/login', (req, res) => {
    const { username, password } = req.body;

    const user = registeredUsers[username];

    if (user && user.password === password) {
        isLoggedIn.push(username);
        res.status(200).json({ message: 'Login successful' });
    } else {
        res.status(401).json({ error: 'Invalid username or password' });
    }
});

/**
 * Route to check if a user is logged in.
 * 
 * @param {Object} req - The request object containing the username to check
 * @param {Object} res - The response object for sending HTTP responses
 */
router.get('/isLoggedIn/:username', (req, res) => {
    const username = req.params.username;
    if (isLoggedIn.includes(username)) {
        res.status(200).json({ message: `${username} is logged in` });
    } else {
        res.status(401).json({ error: `${username} is not logged in` });
    }
});

/**
 * Route to get details of a user.
 * 
 * @param {Object} req - The request object containing the username to fetch details
 * @param {Object} res - The response object for sending HTTP responses
 */
router.get('/getDetails/:username', (req, res) => {
    const username = req.params.username;
    const user = registeredUsers[username];
    if(user) {
        return res.status(200).json(user);
    } else {
        return res.status(404).json({ message: "User not found" });
    }
});

module.exports = router;
