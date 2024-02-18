let currentUser = "";
document.addEventListener("DOMContentLoaded", function () {
  const urlParams = new URLSearchParams(window.location.search);
  const username = urlParams.get("username");

  fetch(
    `http://${window.location.hostname}:3000/api/user/isLoggedIn/${username}`
  )
    .then((response) => {
      if (response.ok) {
        console.log("User is logged in");
      } else {
        console.log(response);
        window.location.href = "login.html";
      }
    })
    .catch((error) => {
      console.error("Error:", error);
      // Redirect to login.html in case of error
      window.location.href = "login.html";
    });

  currentUser = username;
  getUserObject(username).then((data) => {
    let profileImgLeft = document.getElementsByClassName("profile-pic")[0];
    profileImgLeft.src = data.profile_url;
    profileImgLeft.style.width = "40px";
    profileImgLeft.style.height = "40px";
    let profileName = document.getElementsByClassName("profile-text")[0];
    profileName.textContent = data.name;
    let profileUsername = document.getElementsByClassName("profile-subtext")[0];
    profileUsername.textContent = `@${data.user_name}`;
    setTimeout(() => {
      console.log(data);

      socket.emit("join", username, data.name);
    }, 3000);
  });
});

let userObjGlobal;
const socket = io("http://localhost:3000");
let screenWidth = window.innerWidth;
let previousState = {};

/**
 * Opens a chat window for the specified username and displays the chat history.
 *
 * @param {string} username - the username of the user to open the chat window for
 */
function openChat(username) {
  previousState = {
    rightContainerDisplay:
      document.getElementsByClassName("right-container")[0].style.display,
    centerContainerDisplay:
      document.getElementsByClassName("center-container")[0].style.display,
    profileIconContent:
      document.getElementsByClassName("profile-icon")[0].textContent,
    profileImgSrc: document.getElementsByClassName("profile-img")[0].src,
    bottomNavBarDisplay:
      document.getElementsByClassName("bottom-nav-bar")[0].style.display,
    floatingMessageBoxIconDisplay: document.getElementsByClassName(
      "floating-tweet-box-icon"
    )[0].style.display,
    usernameHeadingDisplay:
      document.getElementsByClassName("username-heading")[0].style.display,
  };
  let rightContainer = document.getElementsByClassName("right-container")[0];
  rightContainer.style.display = "block";

  if (screenWidth < 500) {
    console.log("here");
    rightContainer.style.position = "relative";
    let centerContainer =
      document.getElementsByClassName("center-container")[0];
    centerContainer.style.display = "none";

    let profileDiv = document.getElementsByClassName("profile-icon")[0];
    let h3Element = profileDiv.querySelector("h3");
    getUserObject(username).then((data) => {
      h3Element.textContent = data.name;
      let profileImg = document.getElementsByClassName("profile-img")[0];
      profileImg.style.display = "none";

      let backBtn = document.getElementsByClassName("back-button")[0];
      backBtn.style.display = "block";

      profileDiv.insertBefore(profileImg, h3Element);

      let bottomNavBar = document.getElementsByClassName("bottom-nav-bar")[0];
      bottomNavBar.style.display = "none";

      let floatingMessageBoxIcon = document.getElementsByClassName(
        "floating-tweet-box-icon"
      )[0];
      floatingMessageBoxIcon.style.display = "none";

      let heading = document.getElementsByClassName("username-heading")[0];
      heading.style.display = "none";

      h3Element.classList.add = "username-heading";
    });
  } else {
    let heading = document.getElementsByClassName("username-heading")[0];
    heading.textContent = userObjGlobal.name;
  }

  let inputBx = document.getElementsByClassName("chat-input-div")[0];
  inputBx.style.display = "flex";
  let body = document.getElementsByClassName("chat-body")[0];
  body.innerHTML = "";
  let firstPersonName;
  let secondPersonName;
  console.log(username);
  console.log(currentUser);
  getUserObject(username).then((data) => {
    console.log(data);
    firstPersonName = data.name;
    getUserObject(currentUser)
      .then((data) => {
        secondPersonName = data.name;
      })
      .then(() => {
        let sortedFromToUserKey = firstPersonName + "" + secondPersonName;
        console.log("sortedFromToUserKey: ", sortedFromToUserKey);
        sortedFromToUserKey = sortedFromToUserKey.split("").sort().join("");

        fetch(
          `http://${window.location.hostname}:3000/chat/${sortedFromToUserKey}`
        )
          .then((response) => {
            if (!response.ok) {
              throw new Error("Failed to fetch chat data");
            }
            return response.json();
          })
          .then((data) => {
            data.forEach((messageObject) => {
              messageObject.timestamp = new Date(messageObject.timestamp);
              displayMessage(
                messageObject.from,
                messageObject.message,
                messageObject.timestamp
              );
            });
            if (data.length > 0) {
              let lastMessage = data[data.length - 1].message;
              let usernameHeading =
                document.querySelector(".username-heading").textContent;
              console.log(usernameHeading);
              let messageContentTitles = document.querySelectorAll(
                ".message-content-title"
              );

              let messagePreviewDiv = null;
              for (let title of messageContentTitles) {
                let nameDiv = title.querySelector(".message-name");
                if (nameDiv && nameDiv.textContent === usernameHeading) {
                  console.log("messagePreviewDiv");
                  console.log(nameDiv);
                  messagePreviewDiv = title.nextElementSibling.querySelector(".message-preview");
                  break; 
                }
              }
              if (messagePreviewDiv) {
                console.log(messagePreviewDiv);
                messagePreviewDiv.textContent = lastMessage;
              }
            }
          })
          .catch((error) => {
            console.error("Error fetching chat data:", error);
          });
      });
  });
}

/**
 * Asynchronously retrieves user details from the server.
 *
 * @param {string} username - The username of the user to retrieve details for
 * @return {Promise} A Promise that resolves to the user details object
 */
async function getUserObject(username) {
  let response = await fetch(
    `http://${window.location.hostname}:3000/api/user/getDetails/${username}`
  )
    .then((response) => {
      if (response.ok) {
        return response.json();
      } else {
        console.log(response);
        //window.location.href = "login.html";
      }
    })

    .catch((error) => {
      console.error("Error:", error);
      // Redirect to login.html in case of error
       window.location.href = "login.html";
    });
  return response;
}

let hasOpened = false;
socket.on("new-chat", (username) => {
  console.log("new-chat", username);

  let messagesContainer = document.getElementById("messages");
  console.log(messagesContainer.children.length);

  if (messagesContainer.children.length < 2) {
    let noActiveUsersDiv = document.createElement("div");
    noActiveUsersDiv.classList.add("no-active-users");
    noActiveUsersDiv.textContent = "No active users";
    messagesContainer.appendChild(noActiveUsersDiv);
  } else {
    let noActiveUsersDiv =
      document.getElementsByClassName("no-active-users")[0];
    if (noActiveUsersDiv) {
      noActiveUsersDiv.remove();
    }
  }

  getUserObject(username).then((userObj) => {
    userObjGlobal = userObj;
    let parentDiv = document.createElement("div");
    parentDiv.classList.add("message-parent");
    let messageDiv = document.createElement("div");
    messageDiv.classList.add("message");
    console.log("udetails: ", userObj);


    let messageImgDiv = document.createElement("div");
    messageImgDiv.classList.add("message-img");
    let messageImg = document.createElement("img");
    messageImg.src = userObj.profile_url;
    messageImg.alt = "profile-icon";
    messageImg.classList.add("message-icon");
    messageImgDiv.appendChild(messageImg);

    let messageContentDiv = document.createElement("div");
    messageContentDiv.classList.add("message-content");

    let messageContentTitleDiv = document.createElement("div");
    messageContentTitleDiv.classList.add("message-content-title");
    let messageNameDiv = document.createElement("div");
    messageNameDiv.classList.add("message-name");
    messageNameDiv.textContent = userObj.name;
    let usernameWidth = username.length * 9.5;

    messageNameDiv.style.width = `${usernameWidth}px`;
    let messageUsernameDiv = document.createElement("div");
    messageUsernameDiv.classList.add("message-username");
    messageUsernameDiv.textContent = "@" + username;

    let dotDiv = document.createElement("div");
    dotDiv.classList.add("dot");
    dotDiv.textContent = "•";

    let messageDateDiv = document.createElement("div");
    messageDateDiv.classList.add("message-date");
    let currentDate = new Date();
    let month = currentDate.toLocaleString('default', { month: 'short' });
    let day = currentDate.getDate();
    
    messageDateDiv.textContent = month + " " + day;

    messageContentTitleDiv.appendChild(messageNameDiv);
    messageContentTitleDiv.appendChild(messageUsernameDiv);
    messageContentTitleDiv.appendChild(dotDiv);
    messageContentTitleDiv.appendChild(messageDateDiv);

    let messagePreviewDiv = document.createElement("div");
    messagePreviewDiv.classList.add("message-preview");
    messagePreviewDiv.textContent = "";

    messageContentDiv.appendChild(messageContentTitleDiv);
    messageContentDiv.appendChild(messagePreviewDiv);

    messageDiv.appendChild(messageImgDiv);
    messageDiv.appendChild(messageContentDiv);
    if (
      messagesContainer.children.length >= 1 &&
      !hasOpened &&
      screenWidth > 500
    ) {
      openChat(username);
      hasOpened = true;
      const mockEvent = {
        currentTarget: messageDiv,
      };

      messageClicked(mockEvent);
    }
    messageDiv.addEventListener("click", () => {
      if (screenWidth > 500) messageClicked(event);
      openChat(username);
    });

    parentDiv.appendChild(messageDiv);

    messagesContainer.appendChild(parentDiv);
  });
});

/**
 * Asynchronously adds data to the chat.
 *
 * @param {string} sortedFromToUserKey - The key for the chat sorting.
 * @param {Object} messageObject - The message object to be added to the chat.
 * @return {Promise<Object>} A promise that resolves to the JSON response from the server.
 */
async function addDataToChat(sortedFromToUserKey, messageObject) {
  try {
    const response = await fetch(
      `http://localhost:3000/chat/${sortedFromToUserKey}`,
      {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          messageObject: messageObject,
        }),
      }
    );

    if (!response.ok) {
      throw new Error("Failed to post message to chat");
    }

    return response.json();
  } catch (error) {
    console.error("Error posting message to chat:", error);
    throw error;
  }
}

/**
 * Display a message in the chat interface with the specified sender, message content, and timestamp.
 *
 * @param {string} from - the sender of the message
 * @param {string} message - the content of the message
 * @param {Date} timestamp - the timestamp of the message
 */
function displayMessage(from, message, timestamp) {
  if (from !== undefined && message !== undefined) {
    let messageContainer = document.getElementsByClassName("chat-body")[0];
    let parentTextMessage = document.createElement("div");
    parentTextMessage.classList.add("text-message-parent");
    let dateElem = document.createElement("p");
    let currentDate = new Date();
    try {
      let x = timestamp.getDate();
    } catch {
      timestamp = new Date(timestamp);
    }
    if (
      currentDate.getDate() === timestamp.getDate() &&
      currentDate.getMonth() === timestamp.getMonth() &&
      currentDate.getFullYear() === timestamp.getFullYear()
    ) {
      // If on the same day, display only time
      dateElem.textContent = getFormattedTime(timestamp);
    } else {
      // If on different day, display date and time
      dateElem.textContent =
        getFormattedDate(timestamp) + " " + getFormattedTime(timestamp);
    }

    dateElem.classList.add("message-date");
    let messageElement = document.createElement("p");
    if (from == currentUser) {
      parentTextMessage.classList.add("right-align");
      messageElement.classList.add("blue-message");
      dateElem.style.display = "flex";
      dateElem.style.justifyContent = "flex-end";
      dateElem.style.width = message.length * 17 + "px";
    } else {
      messageElement.classList.add("grey-message");
      dateElem.style.display = "flex";
      dateElem.style.justifyContent = "flex-start";
      dateElem.style.width = message.length * 17 + "px";
    }

    // Finding the correct message preview:
    let usernameHeading =
      document.querySelector(".username-heading").textContent;
    let messageContentTitles = document.querySelectorAll(
      ".message-content-title"
    );
    let messagePreviewDiv;
    messageContentTitles.forEach((title) => {
      let nameDiv = title.querySelector(".message-name");
      if (nameDiv && nameDiv.textContent === usernameHeading) {
        messagePreviewDiv = title.nextElementSibling;
      }
    });

    if (screenWidth > 500) {
      messagePreviewDiv.textContent = message;
    }
    messageElement.classList.add("text-message");
    messageElement.textContent = message;
    messageElement.style.width = message.length * 15 + "px";
    parentTextMessage.appendChild(messageElement);

    parentTextMessage.appendChild(dateElem);

    messageContainer.appendChild(parentTextMessage);
  }
}

socket.on("conversation", (messageObject) => {
  console.log(
    "From: " + messageObject.from + ", Message: " + messageObject.message
  );

  displayMessage(
    messageObject.from,
    messageObject.message,
    messageObject.timestamp
  );
});

let messageInput = document.getElementsByClassName("message-input")[0];

document
  .getElementsByClassName("username-btn")[0]
  .addEventListener("click", function () {
    sendMessage();
  });

messageInput.addEventListener("keyup", function (event) {
  if (event.keyCode === 13) {
    sendMessage();
  }
});

/**
 * Sends a message if the input is not empty. 
 * Retrieves the username and full name of the current user, and constructs a usernameCombo based on them. 
 * Emits a chat event to the socket with the sortedFromToUserKey, messageObject, and username. 
 * Displays the sent message on the chat interface. 
 */
function sendMessage() {
  let message = messageInput.value.trim();
  if (message.length > 0) {
    let username =
      document.getElementsByClassName("username-heading")[0].textContent;
    if (screenWidth < 500) {
      username = document.querySelector("h3").textContent;
    }
    let fullName;
    getUserObject(currentUser).then((user) => {
      fullName = user.name;
      let usernameCombo = username + "" + fullName;
      console.log("usernameCombo", usernameCombo);
      let sortedFromToUserKey = usernameCombo.split("").sort().join("");
      let messageObject = {
        from: currentUser,
        message: message,
        timestamp: new Date(),
      };
      socket.emit("chat", sortedFromToUserKey, messageObject, username);
      displayMessage(currentUser, message, new Date());

      messageInput.value = "";
    });
  }
}

let prevParentClicked;
// on click for the message-parent
function messageClicked(event) {
  console.log("clicked", event.target);

  if (prevParentClicked) {
    console.log(prevParentClicked);
    prevParentClicked.style.borderRight = "none";
    prevParentClicked.style.backgroundColor = "black";
  }
  event.currentTarget.style.borderRight = "1px solid #1D9BF0";
  event.currentTarget.style.backgroundColor = "#202327";
  prevParentClicked = event.currentTarget;
}

/** HELPER FUNCTIONS **/

// Function to get formatted timestamp (HH: MM)
function getFormattedTime(date) {
  let hours = date.getHours();
  let minutes = date.getMinutes();
  minutes = minutes < 10 ? "0" + minutes : minutes;
  return hours + ":" + minutes;
}

// Function to get formatted date (DD/MM/YYYY)
function getFormattedDate(date) {
  let day = date.getDate();
  let month = date.getMonth() + 1;
  let year = date.getFullYear();
  return day + "/" + month + "/" + year;
}

/**
 * Function to go back to previous state and display messages.
 */
function goBack() {
  let username = document.querySelector("h3").textContent;
  let messagePreviewDiv = null;
  let messageContentTitles = document.querySelectorAll(
    ".message-content-title"
  );
  messageContentTitles.forEach((title) => {
    let nameDiv = title.querySelector(".message-name");
    if (nameDiv && nameDiv.textContent === username) {
      messagePreviewDiv = title.nextElementSibling;
    }
  });

  let messages = document.getElementsByClassName("text-message");
  let lastMessage = messages[messages.length - 1];
  console.log(lastMessage);
  console.log(messagePreviewDiv);
  if (lastMessage && window.innerWidth > 500)
    messagePreviewDiv.textContent = lastMessage.textContent;

  document.getElementsByClassName("right-container")[0].style.display = "none";
  document.getElementsByClassName("center-container")[0].style.display =
    "block";

  document.querySelector("h3").textContent = "Messages";
  document.getElementsByClassName("profile-img")[0].style.display = "block";
  document.getElementsByClassName("bottom-nav-bar")[0].style.display =
    previousState.bottomNavBarDisplay;
  document.getElementsByClassName("floating-tweet-box-icon")[0].style.display =
    previousState.floatingMessageBoxIconDisplay;
  document.getElementsByClassName("username-heading")[0].style.display =
    "block";
  document.getElementsByClassName("back-button")[0].style.display = "none";
}
