const express = require('express');
const router = express.Router();
const userChat = require("../../data/Socket/chatUserMap");

/**
 * Route to fetch messages between two users identified by sortedFromToUserKey.
 * 
 * @param {Object} req - The request object containing the sortedFromToUserKey parameter
 * @param {Object} res - The response object for sending HTTP responses
 */
router.get("/:sortedFromToUserKey", (req, res) => {
    const sortedFromToUserKey = req.params.sortedFromToUserKey;
    console.log("sortedFromToUserKey: ", sortedFromToUserKey);
    const messageObjects = userChat[sortedFromToUserKey] || [];
    console.log("Message objects fetched:");
    messageObjects.forEach((messageObject, index) => {
        console.log(`Message ${index + 1}: `, messageObject);
    });    
    res.json(messageObjects);
});

/**
 * Route to add a new message to the chat between two users identified by sortedFromToUserKey.
 * 
 * @param {Object} req - The request object containing the sortedFromToUserKey parameter and messageObject in the body
 * @param {Object} res - The response object for sending HTTP responses
 */
router.post("/:sortedFromToUserKey", (req, res) => {
    /**
     * req should have username
     * chat will have key as username
     */
    const sortedFromToUserKey = req.params.sortedFromToUserKey;
    console.log("sortedFromToUserKey: ", sortedFromToUserKey);
    if (!userChat[sortedFromToUserKey]) {
        userChat[sortedFromToUserKey] = [];
    }
    const messageObject = req.body.messageObject;
    console.log(messageObject);

    if (messageObject !== undefined) {
        userChat[sortedFromToUserKey].push(messageObject);
        console.log("User chat updated in memory", userChat[sortedFromToUserKey]);
    } else {
        console.log("messageObject is undefined. Not adding to userChat map.");
    }

    res.json(userChat[sortedFromToUserKey]);
});

module.exports = router;
