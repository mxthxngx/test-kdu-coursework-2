const express = require('express');
const app = express();
const http = require('http');
const socketIO = require('socket.io');
const cors = require('cors');
const chatApi = require("./routes/chat/chat");
const authApi = require("./routes/authenticate/authenticate");
const postApi = require("./routes/posts/posts")
let socketChatMap = require("./data/Socket/socketUserMap");

// Middleware
app.use(express.json());
app.use(cors());
app.use(express.json({ limit: '10GB' }));

// Routes
app.use("/chat", chatApi);
app.use("/api/user", authApi);
app.use("/api/posts", postApi);

// Create HTTP server and socket.io instance
const server = http.createServer(app);
const io = new socketIO.Server(server, {
    cors: {
        origin: "http://127.0.0.1:5500", // Adjust this origin to match your client's origin
    }
});

// Start the server
server.listen(3000, () => {
    console.log("Listening on port 3000");
});

// Helper function to fetch user details
async function getUserObject(username) {
    try {
        let response = await fetch(`http://localhost:3000/api/user/getDetails/${username}`);
        if (response.ok) {
            return response.json();
        } else {
            console.log(response);
        }
    } catch (error) {
        console.error("Error:", error);
    }
}

// Helper function to fetch chat data
async function getChat(sortedFromToUserKey) {
    try {
        let response = await fetch(`http://localhost:3000/chat/${sortedFromToUserKey}`);
        let data = await response.json();
        return data;
    } catch (error) {
        console.error("Error fetching chat data:", error);
    }
}

// Helper function to add data to chat
async function addDataToChat(sortedFromToUserKey, messageObject) {
    try {
        const response = await fetch(`http://localhost:3000/chat/${sortedFromToUserKey}`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                messageObject: messageObject
            })
        });

        if (!response.ok) {
            throw new Error('Failed to post message to chat');
        }

        return response.json();
    } catch (error) {
        console.error("Error posting message to chat:", error);
        throw error;
    }
}

// Socket.io event handling
io.on("connection", (socket) => {
    console.log("New client connected " + socket.id);

    // When a new user joins
    socket.on("join", (username, firstname) => {
        console.log("name recieved ", firstname);
        socketChatMap[firstname] = socket.id;
        console.log(firstname, " joined ", socketChatMap[firstname]);
        io.emit("new-chat", username);
    });

    // When a user opens the chat and tries messaging
    // Here username is the person to whom the message is being sent to.
    socket.on("chat", (sortedFromToUserKey, messageObject, toUserName) => {
        console.log("sending message to ", messageObject);
        getUserObject(messageObject.from).then((data) => {
            if (messageObject && data.name && messageObject.message) {
                if (data.name != toUserName) {
                    console.log("message sent to ", toUserName);
                    io.to(socketChatMap[toUserName]).emit("conversation", messageObject);
                }
                console.log("message sent ", messageObject);
                addDataToChat(sortedFromToUserKey, messageObject);
            } else {
                console.error("Message object is undefined or empty.");
            }
        })


    });
});
