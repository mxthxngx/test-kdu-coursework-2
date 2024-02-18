const express = require('express');
const router = express.Router();
const posts = require("../../data/Posts/posts");
const registeredUsers = require("../../data/Users/registeredUsers");

/**
 * Route to fetch a paginated list of posts.
 * 
 * @param {Object} req - The request object containing the pageSize and page parameters
 * @param {Object} res - The response object for sending HTTP responses
 */
router.get("/:pageSize/:page", (req, res) => {
    const pageSize = parseInt(req.params.pageSize); 
    const page = parseInt(req.params.page); 

    const startIndex = (page - 1) * pageSize;
    const endIndex = page * pageSize;

    const paginatedPosts = posts.slice(startIndex, endIndex);

    res.json(paginatedPosts);
});

/**
 * Route to fetch a single post by its ID.
 * 
 * @param {Object} req - The request object containing the post ID parameter
 * @param {Object} res - The response object for sending HTTP responses
 */
router.get("/:id", (req, res) => {
    const postId = req.params.id;
    const post = posts.find(post => post.id === postId);

    if (!post) {
        return res.status(404).json({ message: "Post not found" });
    }

    res.json(post);
});

/**
 * Route to add a new post.
 * 
 * @param {Object} req - The request object containing post data (username, timestamp, message, img)
 * @param {Object} res - The response object for sending HTTP responses
 */
router.post("/", (req, res) => {
    const { username, timestamp, message, img } = req.body;

    if (!username || !timestamp || !message) {
        return res.status(400).json({ message: "All fields are required" });
    }

    const newPost = {
        id: `post${posts.length + 1}`,
        username,
        timestamp,
        message,
        img
    };

    const user = registeredUsers[username];

    if (!user) {
        return res.status(404).json({ message: "User not found" });
    }

    const updatedPost = {
        ...newPost,
        firstname: user.name,
        profileImg: user.profile_url
    };

    posts.unshift(updatedPost);
    res.status(201).json({ message: "Post added successfully", post: updatedPost });
});

module.exports = router;
