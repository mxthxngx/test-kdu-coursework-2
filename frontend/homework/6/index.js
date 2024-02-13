const express = require("express");
const cors = require("cors");
const fileUpload = require("express-fileupload");
const app = express();
app.use(cors())
app.use(fileUpload());

app.use(express.json());
const userApi = require("./routes/api/posts")

app.use("/api/posts",userApi)

app.get("/",(req,res)=>
{
    res.json({"msg":"hello world"});
})


app.listen(5000,()=>{
    console.log("Appln launched on port 5000")
})
