document.addEventListener('DOMContentLoaded', function () {
  if(window.innerWidth<500)
  {
    const parentElement = document.querySelector('.tweet-box');
    const childElement = document.querySelector('.tweet-box-profile-mobile');
    const toReplace = document.querySelector('.tweet-box-profile');
    parentElement.replaceChild(childElement, toReplace);
  
  
  }

  let postButton = document.querySelector('.tweet-btn');
  let tweetInput = document.querySelector('.post-input');
  let postactivebtn = document.querySelector('.tweet-btn' );
  const tweetBoxProfileMobile= document.querySelector('.tweet-box-profile-mobile');
  const posts = document.getElementsByClassName('posts')[0];
 const profileIcon = document.getElementsByClassName('profile-icon')[0];
 profileIcon.addEventListener('click',showNavigationSection);
  const floatingTweetBoxIcon = document.querySelector('.floating-tweet-box-icon');
console.log(postButton);
let commentBtn = document.querySelector('.tweet-btn-container.comment-btn .comment-button.tweet-btn');

console.log(commentBtn);

let commentInput = document.getElementsByClassName('comment-input')[0];
commentInput.addEventListener('input', function () {
  const tweetContent = commentInput.value;
  console.log("here");
  if (tweetContent.length > 0) {
    console.log("input length");
    commentBtn.style.backgroundColor = '#1D9BF0'; 
    commentBtn.style.color='white';
  } else {
    commentBtn.style.backgroundColor = '';
    commentBtn.style.color='#70797F';
  }
});

commentBtn.addEventListener('click', function () {
  const currentComment = document.getElementsByClassName('comment-1')[0];
  const clonedComment = currentComment.cloneNode(true);

  const postTextElement = clonedComment.querySelector('.post-text');
  const updatedComment = commentInput.value;

  postTextElement.textContent = updatedComment;
  commentInput.value = ''; // Clear the input field after comment is submitted
  document.getElementsByClassName('past-comments')[0].appendChild(clonedComment);
});

  tweetInput.addEventListener('input', function () {
    const tweetContent = tweetInput.value;
console.log("here")
    if (tweetContent.length > 0) {
      console.log("input length")
      postactivebtn.style.backgroundColor = '#1D9BF0'; 
      postactivebtn.style.color='white';
    } else {
      postactivebtn.style.backgroundColor = '';
      postactivebtn.style.color='#70797F';
    }
  });

  floatingTweetBoxIcon.addEventListener('click', function () {
    if (tweetBoxProfileMobile.style.display === 'none') {
        console.log("here")
        tweetBoxProfileMobile.style.display = 'block';
        posts.style.display='none';
        tweetInput.style.display="block";
        profileIcon.style.display='none';
        floatingTweetBoxIcon.style.display='none';

    } else {
        tweetBoxProfileMobile.style.display = 'none';
    }
});

  let backArrow = document.querySelector('.back-arrow');
  backArrow.addEventListener('click', function () {
    tweetBoxProfileMobile.style.display = 'none';
    posts.style.display='block';
    tweetInput.style.display="block";
    profileIcon.style.display='block';
    floatingTweetBoxIcon.style.display='block';
  });

  
  postButton.addEventListener('click', function () {
    const tweetContent = tweetInput.value;
    const newPost = createPostElement(tweetContent);
    if(window.innerWidth<500)
    {
      tweetBoxProfileMobile.style.display='none';
      posts.style.display='block';
      profileIcon.style.display='block';
      floatingTweetBoxIcon.style.display='block';

    }
    const postsContainer = document.querySelector('.posts');
    postsContainer.appendChild(newPost);
    tweetInput.value = '';
    postButton.style.backgroundColor = '';
  });

  const commentIcon = document.querySelector('.comment-icon');
  commentIcon.addEventListener('click', function () {
      let commentbox = document.getElementsByClassName('comment-box')[0];
      if (commentbox.style.display === 'block') {
        commentbox.style.display = 'none'; 
    } else {
        commentbox.style.display = 'block'; 
    }
      
  });
  document.addEventListener('click', function(event) {
    if (event.target && event.target.classList.contains('retweet')) {
            const likeIcon = event.target;
      if (likeIcon.src.includes('retweet.svg')) {
        likeIcon.src = 'images/icons/retweet-blue.svg'; 

        let likeCountElement = likeIcon.parentElement.querySelector('.tweet-count');
        if (!likeCountElement || isNaN(parseInt(likeCountElement.textContent))) {
          likeCountElement = document.createElement('div');
          likeCountElement.classList.add('tweet-count');
          likeCountElement.textContent = '1';
          likeIcon.parentElement.appendChild(likeCountElement);
      } else {
          likeCountElement.textContent = parseInt(likeCountElement.textContent) + 1;
      }
      } else {
        likeIcon.src = 'images/icons/retweet.svg'; 
        let likeCountElement = likeIcon.parentElement.querySelector('.tweet-count');
        if (likeCountElement) {
          let count = parseInt(likeCountElement.textContent);
          if (count > 0) {
        console.log(count)
            if(count == 1){
              likeCountElement.remove();
            }
            else
            {
              likeCountElement.textContent = count - 1;
            }
          }
        }
      }
    }
  });

  document.addEventListener('click', function(event) {
    if (event.target && event.target.classList.contains('like-post')) {
            const likeIcon = event.target;
      if (likeIcon.src.includes('like.svg')) {
        likeIcon.src = 'images/icons/like-pink.svg'; 

        let likeCountElement = likeIcon.parentElement.querySelector('.like-count');
        if (!likeCountElement || likeCountElement==NaN) {
          likeCountElement = document.createElement('div');
          likeCountElement.classList.add('like-count');
          likeCountElement.textContent = '1';
          likeIcon.parentElement.appendChild(likeCountElement);
        } else {
          if(likeCountElement.textContent==NaN)
          {
            likeCountElement.textContent = 0;
          }
          likeCountElement.textContent = parseInt(likeCountElement.textContent) + 1;
        }
      } else {
        likeIcon.src = 'images/icons/like.svg'; 
        let likeCountElement = likeIcon.parentElement.querySelector('.like-count');
        if (likeCountElement) {
          let count = parseInt(likeCountElement.textContent);
          if (count > 0) {
        
            if(count == 1){
              likeCountElement.remove();
            }
            else
            {
              likeCountElement.textContent = count - 1;
            }
          }
        }
      }
    }
  });
});

function createPostElement(content) {
  const postDiv = document.createElement('div');
  postDiv.classList.add('post');

  const postProfileDiv = document.createElement('div');
  postProfileDiv.classList.add('post-profile');

  const profileImg = document.createElement('img');
  profileImg.src = 'images/Profile/profile pic.png';
  profileImg.alt = 'profile-icon';
  profileImg.classList.add('profile-img-post');

  const postTextDiv = document.createElement('div');
  postTextDiv.classList.add('post-text-including-icon');

  const postProfileTextDiv = document.createElement('div');
  postProfileTextDiv.classList.add('post-profile-text');

  const nameParagraph = document.createElement('p');
  nameParagraph.classList.add('post-profile-name');
  nameParagraph.textContent = 'Nitesh Gupta';

  const usernameParagraph = document.createElement('p');
  usernameParagraph.classList.add('post-profile-username');
  usernameParagraph.textContent = '@nit_hck • 0s';

  const dotsIcon = document.createElement('img');
  dotsIcon.src = 'images/icons/dots-grey.svg';
  dotsIcon.alt = 'more-icon';
  dotsIcon.classList.add('dot-icon');

  content = content.replace(/#(\w+)/g, '<span class="hashtags">#$1</span>');

  const postContentDiv = document.createElement('div');
  postContentDiv.classList.add('post-text');
  postContentDiv.innerHTML = content; 

  const postIconsDiv = document.createElement('div');
  postIconsDiv.classList.add('post-icons');

  const commentIcon = document.createElement('div');
  commentIcon.classList.add('comment');
  commentIcon.innerHTML = '<img src="images/icons/comment.svg" alt="comment-icon" class="comment-icon"/>';

  const retweetIcon = document.createElement('div');
  retweetIcon.classList.add('retweet');
  retweetIcon.innerHTML = '<img src="images/icons/retweet.svg" alt="retweet" class="retweet"/>';

  const likeIcon = document.createElement('div');
  likeIcon.classList.add('like');
  likeIcon.innerHTML = '<img src="images/icons/like.svg" alt="like" class="like-post"/>';

  const statsIcon = document.createElement('div');
  statsIcon.classList.add('stats');
  statsIcon.innerHTML = '<img src="images/icons/stats.svg" alt="stats" class="stats"/>';

  const bookmarkShareIcon = document.createElement('div');
  bookmarkShareIcon.classList.add('bookmark-share');
  bookmarkShareIcon.innerHTML = '<img src="images/icons/bookmark-grey.svg" alt="bookmark-icon" class="bookmark-icon"/><img src="images/icons/share.svg" alt="share" class="share-icon"/>';

  const postTextWhole = document.createElement('div');
  postTextWhole.classList.add('post-profile-whole-text');

  postProfileTextDiv.appendChild(nameParagraph);
  postProfileTextDiv.appendChild(usernameParagraph);
  postTextDiv.appendChild(postProfileTextDiv);
  postTextDiv.appendChild(dotsIcon);
  postTextWhole.appendChild(postTextDiv);
  postTextWhole.appendChild(postContentDiv);
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

function showNavigationSection() {
  let navSection = document.getElementsByClassName("navigation-section")[0];
  navSection.style.display = "flex";
  navSection.style.paddingLeft = '15px';
  navSection.style.flexDirection = "column-reverse";
  let xLogo = document.getElementsByClassName("logo-img")[0]
  xLogo.style.display = "none";
  let home = document.getElementsByClassName("home-div")[0]
  home.style.display = "none";
  let profileimg = document.getElementsByClassName("profile-icon")[0];
  profileimg.style.display="none";
  let postBtn = document.getElementsByClassName("post-btn-1")[0];
  postBtn.style.display="none";
  let moreDiv = document.getElementsByClassName("more-div")[0];
  moreDiv.style.display="none";
  let grokDiv = document.getElementsByClassName("grok-div")[0];
  grokDiv.style.display="none";
  let notifDiv = document.getElementsByClassName("notification-div")[0];
  notifDiv.style.display="none";
  let navigationPanel = document.getElementsByClassName("navigation-panel")[0];
  navigationPanel.style.paddingBottom="10px";
  navigationPanel.style.fontSize="14px";
  navigationPanel.style.fontWeight="bold";
  let profileSection = document.getElementsByClassName("profile-section")[0];
  profileSection.style.marginTop="10px";
  profileSection.style.flexDirection="column";
  profileSection.style.alignItems="flex-start";
  let body = document.querySelector('body');
  changeBackgroundColor("#242D34",body);
  changeBackgroundColor("#000",navSection)  
}

function changeBackgroundColor(color,element) {
  element.style.backgroundColor = color;
}
