/**
 * npm init -y
 * npm install -D nodemon
 * npm install socket.io
 */
const express = require('express');
const cors = require('cors')
const http = require('http');
const socketIO = require('socket.io');
const app = express();
const server = http.createServer(app);
const io = new socketIO.Server(server,{
    cors:{
        origin: "http://127.0.0.1:5500",
    }
});

app.use(cors());
app.use(express.json());
app.get("/", (req, res) => {
    res.json({msg:"Hello World"})
});
io.on("connection",(socket)=> {
    console.log("New client connected");
    socket.on("message",(message)=> {
        console.log("message ",message);
        io.except(socket.id).emit("new-message",message);
    })
});
server.listen(3000,()=> {
    console.log("Listening on port 3000");
});