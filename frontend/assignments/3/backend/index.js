const express = require('express');
const app = express();
const http = require('http');
const socketIO = require('socket.io');
const cors = require('cors');
const usernames  = require("./data/userData")

app.use(express.json());
app.use(cors({
  origin: 'http://localhost:5173' 
}));

const server = http.createServer(app);

const dashboardAPI = require('./routes/dashboard/dashboard');
const priceAPI = require('./routes/dashboard/priceGenerator');
const userAPI = require('./routes/user/User')
const portfolioAPI = require('./routes/portfolio/portfolio');

app.use('/dashboard', dashboardAPI);
app.use('/dashboard/price', priceAPI);
app.use('/user',userAPI);
app.use('/portfolio',portfolioAPI);
const io = socketIO(server, {
  cors: {
    origin: "http://localhost:5173", 
    methods: ["GET", "POST"]
  }
});
const socketUserMap = {};

io.on('connection', (socket) => {
  console.log('a user connected');
  const userData = generateRandomUserData();
  const {username,  walletAmount} = userData;
  console.log("username assigned: ",username,",Wallet amt: ",walletAmount)
  socketUserMap[socket.id] = username;
  if (!usernames.find(user => user.username === username)) {
    usernames.push(userData);
}
socket.emit('username', username);
  socket.on('transaction', (symbol , data) => {
    console.log("TRANSACTION",symbol,data,socketUserMap[socket.id])
    socket.broadcast.to(symbol).emit('global', socketUserMap[socket.id], data,symbol);
  });
  socket.on('join-room',(symbol)=>{
    socket.join(symbol);
    console.log("User joined room: " + symbol);
  })
});

server.listen(3000, () => {
  console.log("Listening on port 3000");
});
/**
 * Generates random user data including a username and wallet amount.
 *
 * @return {Object} An object containing the generated username and wallet amount
 */
function generateRandomUserData() {
  const adjectives = ['Red', 'Green', 'Blue', 'Yellow', 'Purple', 'Orange', 'Pink', 'Black', 'White'];
  const nouns = ['Apple', 'Banana', 'Cherry', 'Grape', 'Lemon', 'Orange', 'Peach', 'Pear', 'Strawberry'];
  const adjective = adjectives[Math.floor(Math.random() * adjectives.length)];
  const noun = nouns[Math.floor(Math.random() * nouns.length)];
  const username = `${adjective}${noun}`;
  const walletAmount = Math.floor(Math.random() * 100000); 
  return { username, walletAmount };
}
