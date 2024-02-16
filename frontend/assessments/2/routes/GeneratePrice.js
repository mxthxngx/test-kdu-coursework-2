const express = require('express');
const router = express.Router();

let buySell = ["BUY","SELL"];
router.get("/",(req,res)=> {
   console.log();
   return res.json( {
    price:(Math.random()*(500-0)+0).toFixed(2),
    time:Date.now(),
    type: buySell[Math.floor(Math.random()*(buySell.length-0)+0)],
    stockName:"Zomato"
   })
})

module.exports = router