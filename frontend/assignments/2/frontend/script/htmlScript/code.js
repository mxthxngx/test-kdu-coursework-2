/**
 * Executes the code after the DOM content has been fully loaded.
 * Performs tasks such as fetching user data, fetching posts, handling image uploads, and event listeners.
 */

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


    fetch(`http://${window.location.hostname}:3000/api/user/getDetails/${username}`)
    .then((response) => {
      if (response.ok) {
        return response.json();
      } else {
        console.log(response);
        window.location.href = "login.html";
      }
    })
    .then((userData) => 
    {
      console.log(userData);
      let profileDiv = document.getElementsByClassName("profile-img-post")[0];
      profileDiv.src = userData.profile_url;
      profileDiv.style.width="40px";
      profileDiv.style.height="40px";
      let profileIconDiv = document.getElementsByClassName("profile-pic")[0];
      profileIconDiv.style.width="40px";
      profileIconDiv.style.height="40px";
      profileIconDiv.src = userData.profile_url;
      let profileNameDiv = document.getElementsByClassName("profile-text")[0];
      profileNameDiv.textContent = userData.name;
    
      let profileUsernameDiv = document.getElementsByClassName("profile-subtext")[0];
      profileUsernameDiv.textContent = " @"+userData.user_name;
      if(window.innerWidth<500)
      {
        let profileIcon = document.querySelector(".main-container .profile-icon img");
        console.log(profileIcon)
        profileIcon.src = userData.profile_url;
        profileIcon.style.width="40px";
        profileIcon.style.height="40px";
      }
    })
    .catch((error) => {
      console.error("Error:", error);
      // Redirect to login.html in case of error
      window.location.href = "login.html";
    });


  const postsDiv = document.getElementsByClassName("posts")[0];
  fetch(`http://${window.location.hostname}:3000/api/posts/5/1`)
    .then((response) => response.json())
    .then((posts) => {
      posts.forEach((post) => {
        const postElement = createPostElement(post);
        postsDiv.appendChild(postElement);
      });
    })
    .catch((error) => {
      console.error("Error fetching posts:", error);
    });

  window.addEventListener("scroll", function () {
    if (
      window.innerHeight + window.scrollY >=
      document.body.offsetHeight *.95
    ) {
      fetchMorePosts();
    }
  });

  if (window.innerWidth < 500) {
    const parentElement = document.querySelector(".tweet-box");
    const childElement = document.querySelector(".tweet-box-profile-mobile");
    const toReplace = document.querySelector(".tweet-box-profile");
    parentElement.replaceChild(childElement, toReplace);
    console.log(toReplace)
  }
  let imageInput = document.querySelector("#imageInput");
  let tweetBox = document.querySelector(".tweet-box");
  let postButton = document.querySelector(".tweet-btn");
  console.log("postbtn: ", postButton);
  let tweetInput = document.querySelector(".post-input");
  const tweetBoxProfileMobile = document.querySelector(
    ".tweet-box-profile-mobile"
  );
  const posts = document.getElementsByClassName("posts")[0];
  const profileIcon = document.getElementsByClassName("profile-icon")[0];
  imageInput.addEventListener("change", handleImageInput);
  profileIcon.addEventListener("click", showNavigationSection);
  const floatingTweetBoxIcon = document.querySelector(
    ".floating-tweet-box-icon"
  );
  console.log(postButton);
  let commentBtn = document.querySelector(
    ".tweet-btn-container.comment-btn .comment-button.tweet-btn"
  );

  console.log(commentBtn);


  tweetInput.addEventListener("input", function () {
    const tweetContent = tweetInput.value.trim();
    console.log("here");
    if (tweetContent.length > 0) {
      console.log("input length");
      postButton.style.backgroundColor = "#1D9BF0";
      postButton.style.color = "white";
    } else {
      postButton.style.backgroundColor = "";
      postButton.style.color = "";
    }
  });

  floatingTweetBoxIcon.addEventListener("click", function () {
    if (tweetBoxProfileMobile.style.display === "none") {
      console.log("here");
      tweetBoxProfileMobile.style.display = "block";
      posts.style.display = "none";
      tweetBox.style.display = "flex";

      tweetInput.style.display = "block";
      profileIcon.style.display = "none";
      floatingTweetBoxIcon.style.display = "none";
    } else {
      tweetBoxProfileMobile.style.display = "none";
    }
  });

  let backArrow = document.querySelector(".back-arrow");
  backArrow.addEventListener("click", function () {
    tweetBoxProfileMobile.style.display = "none";
    posts.style.display = "block";
    tweetInput.style.display = "block";
    profileIcon.style.display = "flex";
    floatingTweetBoxIcon.style.display = "flex";
    tweetBox.style.display = "none";
  });

  postButton.addEventListener("click", function () {
    const tweetContent = tweetInput.value.trim();
    console.log("clicked: ", tweetContent);
    if (tweetContent.length > 0) {
      let img = null;

      if (isImageUploaded()) {
        img = document.querySelector(".post-input-image img").src;
      }
      console.log(imagePath)
      const newPost = {
        username: username,
        timestamp: new Date().getTime(),
        message: tweetContent,
        img: img == null ? null : img,
      };

      const newPostJSON = JSON.stringify(newPost);

      // Calculate the payload size in bytes
      const payloadSizeInBytes = new Blob([newPostJSON]).size;
    
      console.log("Payload size of newPost object:", payloadSizeInBytes, "bytes");
      console.log(newPost);
      fetch(`http://${window.location.hostname}:3000/api/posts`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(newPost),
      })
        .then((response) => {
          if (response.ok) {
            return response.json();
          } else {
            throw new Error("Failed to add post");
          }
        })
        .then((post) => {
          createPostElement(post);
          if (window.innerWidth < 500) {
            tweetBoxProfileMobile.style.display = "none";
            posts.style.display = "block";
            profileIcon.style.display = "flex";
            floatingTweetBoxIcon.style.display = "flex";
            tweetBox.style.display = "none";
          }
          window.location.reload();

          tweetInput.value = "";
          postButton.style.backgroundColor = "";
          postButton.style.color = "";
        });
    }
  });

 
  document.addEventListener("click", function (event) {
    if (event.target && event.target.classList.contains("retweet")) {
      const likeIcon = event.target;
      if (likeIcon.src.includes("retweet.svg")) {
        likeIcon.src = "images/icons/retweet-blue.svg";

        let likeCountElement =
          likeIcon.parentElement.querySelector(".tweet-count");
        if (
          !likeCountElement ||
          isNaN(parseInt(likeCountElement.textContent))
        ) {
          likeCountElement = document.createElement("div");
          likeCountElement.classList.add("tweet-count");
          likeCountElement.textContent = "1";
          likeIcon.parentElement.appendChild(likeCountElement);
        } else {
          likeCountElement.textContent =
            parseInt(likeCountElement.textContent) + 1;
        }
      } else {
        likeIcon.src = "images/icons/retweet.svg";
        let likeCountElement =
          likeIcon.parentElement.querySelector(".tweet-count");
        if (likeCountElement) {
          let count = parseInt(likeCountElement.textContent);
          if (count > 0) {
            console.log(count);
            if (count == 1) {
              likeCountElement.remove();
            } else {
              likeCountElement.textContent = count - 1;
            }
          }
        }
      }
    }
  });

  document.addEventListener("click", function (event) {
    if (event.target && event.target.classList.contains("like-post")) {
      const likeIcon = event.target;
      if (likeIcon.src.includes("like.svg")) {
        likeIcon.src = "images/icons/like-pink.svg";

        let likeCountElement =
          likeIcon.parentElement.querySelector(".like-count");
        if (!likeCountElement || likeCountElement.isNaN) {
          likeCountElement = document.createElement("div");
          likeCountElement.classList.add("like-count");
          likeCountElement.textContent = "1";
          likeIcon.parentElement.appendChild(likeCountElement);
        } else {
          if (likeCountElement.textContent.isNaN) {
            likeCountElement.textContent = 0;
          }
          likeCountElement.textContent =
            parseInt(likeCountElement.textContent) + 1;
        }
      } else {
        likeIcon.src = "images/icons/like.svg";
        let likeCountElement =
          likeIcon.parentElement.querySelector(".like-count");
        if (likeCountElement) {
          let count = parseInt(likeCountElement.textContent);
          if (count > 0) {
            if (count == 1) {
              likeCountElement.remove();
            } else {
              likeCountElement.textContent = count - 1;
            }
          }
        }
      }
    }
  });
});

/**
 * Creates and returns a new post element based on the given content.
 *
 * @param {string} content - the content of the post
 * @return {Element} the newly created post element
 */
function createPostElement(postObj) {
  const postDiv = document.createElement("div");
  postDiv.classList.add("post");

  const postProfileDiv = document.createElement("div");
  postProfileDiv.classList.add("post-profile");

  const profileImg = document.createElement("img");
  profileImg.src = postObj.profileImg;
  profileImg.style.borderRadius = "50%";
  profileImg.style.width = "40px";
  profileImg.alt = "profile-icon";
  profileImg.classList.add("profile-img-post");

  const postTextDiv = document.createElement("div");
  postTextDiv.classList.add("post-text-including-icon");

  const postProfileTextDiv = document.createElement("div");
  postProfileTextDiv.classList.add("post-profile-text");

  const nameParagraph = document.createElement("p");
  nameParagraph.classList.add("post-profile-name");
  nameParagraph.textContent = postObj.firstname;

  const usernameParagraph = document.createElement("p");
  usernameParagraph.classList.add("post-profile-username");
  usernameParagraph.textContent = ` @${postObj.username} • ${getTimeDifference(
    postObj.timestamp
  )}`;

  const dotsIcon = document.createElement("img");
  dotsIcon.src = "images/icons/dots-grey.svg";
  dotsIcon.alt = "more-icon";
  dotsIcon.classList.add("dot-icon");

  postObj.message = postObj.message.replace(
    /#(\w+)/g,
    '<span class="hashtags">#$1</span>'
  );

  const postContentDiv = document.createElement("div");
  postContentDiv.classList.add("post-text");
  postContentDiv.innerHTML = postObj.message;

  const postIconsDiv = document.createElement("div");
  postIconsDiv.classList.add("post-icons");

  const commentIcon = document.createElement("div");
  commentIcon.classList.add("comment");
  commentIcon.innerHTML =
    '<img src="images/icons/comment.svg" alt="comment-icon" class="comment-icon"/>';

  const retweetIcon = document.createElement("div");
  retweetIcon.classList.add("retweet");
  retweetIcon.innerHTML =
    '<img src="images/icons/retweet.svg" alt="retweet" class="retweet"/>';

  const likeIcon = document.createElement("div");
  likeIcon.classList.add("like");
  likeIcon.innerHTML =
    '<img src="images/icons/like.svg" alt="like" class="like-post"/>';

  const statsIcon = document.createElement("div");
  statsIcon.classList.add("stats");
  statsIcon.innerHTML =
    '<img src="images/icons/stats.svg" alt="stats" class="stats"/>';

  const bookmarkShareIcon = document.createElement("div");
  bookmarkShareIcon.classList.add("bookmark-share");
  bookmarkShareIcon.innerHTML =
    '<img src="images/icons/bookmark-grey.svg" alt="bookmark-icon" class="bookmark-icon"/><img src="images/icons/share.svg" alt="share" class="share-icon"/>';

  const postTextWhole = document.createElement("div");
  postTextWhole.classList.add("post-profile-whole-text");

  const postImageDiv = document.createElement("div");
  if (postObj.img) {
    postImageDiv.classList.add("post-image");
    const img = document.createElement("img");
    img.src = postObj.img;
    postImageDiv.appendChild(img);
  }
  postProfileTextDiv.appendChild(nameParagraph);
  postProfileTextDiv.appendChild(usernameParagraph);
  postTextDiv.appendChild(postProfileTextDiv);
  postTextDiv.appendChild(dotsIcon);
  postTextWhole.appendChild(postTextDiv);
  postTextWhole.appendChild(postContentDiv);
  postTextWhole.appendChild(postImageDiv);
  postProfileDiv.appendChild(profileImg);
  postProfileDiv.appendChild(postTextWhole);
  postDiv.appendChild(postProfileDiv);
  postIconsDiv.appendChild(commentIcon);
  postIconsDiv.appendChild(retweetIcon);
  postIconsDiv.appendChild(likeIcon);
  postIconsDiv.appendChild(statsIcon);
  postIconsDiv.appendChild(bookmarkShareIcon);
  postDiv.appendChild(postIconsDiv);

  return postDiv;
}

/**
 * Function to show the navigation section and hide certain elements on the page.
 *
 * @param None
 * @return None
 */
function showNavigationSection() {
  const floatingTweetBoxIcon = document.querySelector(
    ".floating-tweet-box-icon"
  );

  floatingTweetBoxIcon.style.display = "none";
  let navSection = document.getElementsByClassName("navigation-section")[0];
  navSection.style.display = "flex";
  navSection.style.paddingLeft = "15px";
  navSection.style.flexDirection = "column-reverse";
  let xLogo = document.getElementsByClassName("logo-img")[0];
  xLogo.style.display = "none";
  let home = document.getElementsByClassName("home-div")[0];
  home.style.display = "none";
  let profileimg = document.getElementsByClassName("profile-icon")[0];
  profileimg.style.display = "none";
  let postBtn = document.getElementsByClassName("post-btn-1")[0];
  postBtn.style.display = "none";
  let moreDiv = document.getElementsByClassName("more-div")[0];
  moreDiv.style.display = "none";
  let grokDiv = document.getElementsByClassName("grok-div")[0];
  grokDiv.style.display = "none";
  let notifDiv = document.getElementsByClassName("notification-div")[0];
  notifDiv.style.display = "none";
  let centralContainer = document.getElementsByClassName("center-container")[0];
  centralContainer.style.display = "none";
  let navigationPanel = document.getElementsByClassName("navigation-panel")[0];
  navigationPanel.style.paddingBottom = "10px";
  navigationPanel.style.fontSize = "14px";
  navigationPanel.style.fontWeight = "bold";
  let profileSection = document.getElementsByClassName("profile-section")[0];
  profileSection.style.marginTop = "10px";
  profileSection.style.flexDirection = "column";
  profileSection.style.alignItems = "flex-start";
  let body = document.querySelector("body");
  changeBackgroundColor("#242D34", body);
  changeBackgroundColor("#000", navSection);
}

/**
 * Sets the background color of the specified element.
 *
 * @param {string} color - The color to set as the background color
 * @param {HTMLElement} element - The element whose background color will be changed
 * @return {void} 
 */
function changeBackgroundColor(color, element) {
  element.style.backgroundColor = color;
}

/**
 * Handles the image selection by clicking the image input.
 *
 * @param None
 * @return None
 */
function handleImageSelection() {
  imageInput.click();
}

/**
 * Handle the image input event.
 *
 * @param {Event} e - The input event
 * @return {void}
 */
let imagePath ;
function handleImageInput(e) {
  const file = e.target.files[0];
  imagePath = URL.createObjectURL(file);
  displayImage(file);
}

/**
 * Display an image from the given file.
 *
 * @param {File} file - The file to display as an image
 * @return {void}
 */
function displayImage(file) {
  const reader = new FileReader();
  let img = document.createElement("img");
  /**
   * Sets the src attribute of an image element with the result of the FileReader onload event,
   * clears the content of a specific image container, and appends the image element to it.
   */
  reader.onload = function (e) {
    img.src = e.target.result;
    const imgContainers = document.querySelectorAll(".post-input-image");
    const imgContainer =
      window.innerWidth < 500 && imgContainers.length > 1
        ? imgContainers[1]
        : imgContainers[0];
    console.log(document.querySelectorAll(".post-input-image"));
    imgContainer.innerHTML = "";
    imgContainer.appendChild(img);
    activatPostBtn();
  };
  reader.readAsDataURL(file);
}

/**
 * Activates the post button by changing its background color, enabling it, and changing its text color.
 *
 */
function activatPostBtn() {
  let postButton = document.querySelector(".tweet-btn");
  postButton.style.backgroundColor = "#1D9BF0";
  postButton.disabled = false;
  postButton.style.color = "white";
}

/**
 * Deactivates the post button by disabling it and updating its style.
 *
 * @return {void}
 */
function deactivatePostBtn() {
  let postactivebtn = document.querySelector(".tweet-btn");
  postactivebtn.disabled = true;
  postactivebtn.style.backgroundColor = "";
  postactivebtn.style.color = "#70797F";
}

/**
 * Check if an image is uploaded.
 *
 * @return {boolean} true if an image is uploaded, false otherwise
 */
function isImageUploaded() {
  const img = document.querySelector(".post-input-image img");
  if (img) return true;
  return false;
}

/**
 * Calculates the time difference between the given timestamp and the current time, and returns a string representation of the time difference in seconds, minutes, hours, or days.
 *
 * @param {number} timestamp - The timestamp to calculate the difference from.
 * @return {string} The string representation of the time difference.
 */
function getTimeDifference(timestamp) {
  const postTime = new Date(timestamp);
  const currentTime = new Date();
  const differenceInSeconds = Math.floor((currentTime - postTime) / 1000);

  if (differenceInSeconds < 60) {
    return `${differenceInSeconds}s`;
  } else if (differenceInSeconds < 3600) {
    return `${Math.floor(differenceInSeconds / 60)}m`;
  } else if (differenceInSeconds < 86400) {
    return `${Math.floor(differenceInSeconds / 3600)}h`;
  } else {
    return `${Math.floor(differenceInSeconds / 86400)}d`;
  }
}
let page = 2;
/**
 * Fetches more posts from the API and appends them to the DOM.
 *
 * @param {type} paramName - description of parameter
 * @return {type} description of return value
 */
function fetchMorePosts() {
  console.log("fetching data: ", page);
  const pageSize = 5;
  fetch(`http://${window.location.hostname}:3000/api/posts/${pageSize}/${page}`)
    .then((response) => response.json())
    .then((posts) => {
      posts.forEach((post) => {
        const postElement = createPostElement(post);
        const postsDiv = document.getElementsByClassName("posts")[0];
        postsDiv.appendChild(postElement);
      });
    })
    .catch((error) => {
      console.error("Error fetching more posts:", error);
    });
  page = page + 1;
}
