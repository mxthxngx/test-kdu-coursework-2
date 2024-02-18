# Node.js Application README

## Overview

This Node.js application provides functionalities for user authentication, posting, and real-time chatting. It utilizes Express.js for routing, Socket.IO for real-time communication, and CORS for enabling cross-origin requests.

## Server Setup

To start the server, run the following command:

```
npm run start-server.js
```

The server will start running on port 3000.

## APIs

### Authentication

#### Login

- **Endpoint:** `POST /api/user/login`
- **Description:** Authenticates users based on provided credentials.
- **Request Body:**
  - `username`: User's username
  - `password`: User's password

### Posts

#### Fetch Posts

- **Endpoint:** `GET /api/posts/:pageSize/:page`
- **Description:** Retrieves paginated posts.
- **Parameters:**
  - `pageSize`: Number of posts per page
  - `page`: Page number
- **Example:** `GET /api/posts/5/1` (Fetches 5 posts from page 1)

#### Get Post by ID

- **Endpoint:** `GET /api/posts/:id`
- **Description:** Retrieves a post by its ID.
- **Parameters:**
  - `id`: ID of the post
- **Example:** `GET /api/posts/post1` (Fetches post with ID "post1")

#### Create Post

- **Endpoint:** `POST /api/posts/`
- **Description:** Creates a new post.
- **Request Body:**
  - `username`: User's username
  - `timestamp`: Timestamp of the post
  - `message`: Content of the post
  - `img`: Image URL (optional)

### Real-time Chat

#### Fetch Chat

- **Endpoint:** `GET /chat/:sortedFromToUserKey`
- **Description:** Retrieves chat messages.
- **Parameters:**
  - `sortedFromToUserKey`: Key for identifying chat participants

#### Add Message to Chat

- **Endpoint:** `POST /chat/:sortedFromToUserKey`
- **Description:** Adds a new message to the chat.
- **Parameters:**
  - `sortedFromToUserKey`: Key for identifying chat participants
- **Request Body:**
  - `messageObject`: Object containing message details

## Usage

- Make requests to the specified endpoints using appropriate methods (e.g., POST, GET).
- Handle real-time chat using Socket.IO events.

## Dependencies

- Express.js
- Socket.IO
- CORS