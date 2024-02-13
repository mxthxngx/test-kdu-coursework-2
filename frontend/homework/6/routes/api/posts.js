
const uuid = require('uuid');
const express = require('express')
let posts =require("../../data/Posts");

const router = express.Router();

router.post('/upload', (req, res) => {
    try{
    posts.push(
       { id:uuid.v4(),
        content: req.body,
        img: req.files
       }
    )
    console.log(req.body);
    console.log(posts);
    res.json({ message: "Upload successful" });
    }
    catch(error)
    {
        res.status(500).json({ error: error.message });
    }
});

router.get("/", (req, res) => {
    res.json(posts);
})


router.get("/:id",(req,res)=>
{
    const id = req.params.id;
    const post = posts.find(post => post.id == id);
    if(!post)
    {
        res.status(404).json({error:"Post not found"});
    }
  else  res.json(post);
})
module.exports = router;