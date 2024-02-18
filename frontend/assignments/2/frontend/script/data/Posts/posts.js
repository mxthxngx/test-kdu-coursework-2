const registeredUsers = require("../../data/Users/registeredUsers");

let posts = [
    {
        id: "post1",
        username: "mathangi",
        firstname: registeredUsers["mathangi"].name,
        timestamp: getRandomTimestamp(),
        message: "This is a sample post by Mathangi.",
        img: "https://images.pexels.com/photos/206359/pexels-photo-206359.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
        profileImg: registeredUsers["mathangi"].profile_url
    },
    {
        id: "post2",
        username: "jane_smith",
        firstname: registeredUsers["jane_smith"].name,
        timestamp: getRandomTimestamp(),
        message: "Hello from Jane Smith!",
        profileImg: registeredUsers["jane_smith"].profile_url
    },
    {
        id: "post3",
        username: "michael_john",
        firstname: registeredUsers["michael_johnson"].name,
        timestamp: getRandomTimestamp(),
        message: "Michael John here, checking in!",
        profileImg: registeredUsers["michael_johnson"].profile_url
    },
    {
        id: "post4",
        username: "sarah_brown",
        firstname: registeredUsers["sarah_brown"].name,
        timestamp: getRandomTimestamp(),
        message: "Sarah Brown says hi to everyone!",
        img: "https://images.pexels.com/photos/906150/pexels-photo-906150.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
        profileImg: registeredUsers["sarah_brown"].profile_url
    },
    {
        id: "post5",
        username: "mathangi",
        firstname: registeredUsers["mathangi"].name,
        timestamp: getRandomTimestamp(),
        message: "Another post by Mathangi!",
        profileImg: registeredUsers["mathangi"].profile_url
    },
    {
        id: "post6",
        username: "jane_smith",
        firstname: registeredUsers["jane_smith"].name,
        timestamp: getRandomTimestamp(),
        message: "Jane Smith checking in again!",
        profileImg: registeredUsers["jane_smith"].profile_url
    }
];

for (let i = 0; i < 10; i++) {
    let randUser = getRandomUsername();
    posts.push({
        id: `post${i + 7}`,
        username: randUser,
        firstname: registeredUsers[randUser].name,
        timestamp: getRandomTimestamp(),
        message: `Random post ${i + 1}`,
        profileImg: registeredUsers[randUser].profile_url
    });
}


module.exports = posts;

function getRandomTimestamp() {
  const currentTime = new Date().getTime();
  const randomTimeDifference = Math.floor(Math.random() * 30 * 24 * 60 * 60 * 1000);
  const randomTimestamp = new Date(currentTime - randomTimeDifference).toISOString();
  return randomTimestamp;
}
function getRandomUsername() {
    const usernames = ["mathangi", "jane_smith", "michael_johnson", "sarah_brown"];
    return usernames[Math.floor(Math.random() * usernames.length)];
}
posts.sort((a, b) => new Date(b.timestamp) - new Date(a.timestamp));
