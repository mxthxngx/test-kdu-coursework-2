const express = require('express');
const app = express();
const http = require('http');
const socketIO = require('socket.io');
const cors = require('cors');

app.use(express.json());
app.use(cors());
const GeneratePriceAPI = require('./routes/GeneratePrice');
const HistoryAPI = require('./routes/HistoryRoute');

app.use("/price",GeneratePriceAPI);
app.use("/history",HistoryAPI);
const server = http.createServer(app);
const io = new socketIO.Server(server, {
    cors: {
        origin: "http://127.0.0.1:5501",
    }
});
server.listen(3000, () => {
    console.log("Listening on port 3000");
});

async function getPrice()
{
    let response = await fetch("http://127.0.0.1:3000/price")
    let data = await response.json(); 

    return data;
}
io.on('connection', (socket) => {
    console.log('New client connected');
    let intervalId; 
    socket.on('load', (msg) => {
        console.log("received msg");
        intervalId = setInterval(async () => {
            try {
                let response = await getPrice();
                console.log(response)
                io.emit("stock-detail", response); 
            } catch (error) {
                console.error("Error fetching price:", error);
            }
        }, 1000);
    });

    socket.on('disconnect', () => {
        clearInterval(intervalId); 
        console.log('Client disconnected');
    });
});
