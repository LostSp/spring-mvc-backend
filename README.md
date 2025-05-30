# Spring MVC Backend Project


## Overview

This project is a backend REST API built using Java 8, Spring MVC, MyBatis, and MySQL. The application supports user registration, login with JWT authentication, post creation and listing, comment management, login tracking, and weekly login ranking.

The application and database are containerized using Docker Compose for easy setup and running.

---

## Technology Stack

- Java 8
- Spring MVC
- Maven
- MyBatis
- MySQL
- Docker & Docker Compose
- JWT for authentication
- BCrypt for password encryption

---

## Features

- User registration with email validation, password encryption, and Korean username
- User login returning a JWT token with 20 minutes expiration
- User information update (password and username)
- Post creation, listing with pagination, and detailed view
- Comment creation, listing (cursor-based pagination), and deletion
- User login records tracking (login time and IP address)
- Weekly login count rankings

---

## Running the Project



### Prerequisites

- Docker and Docker Compose installed

## Development Environment Setup

set up a local development environment using Docker Compose. Please configure the infrastructure environment according to the specs below:

1. The Java backend application and MySQL database are configured in separate containers.  
   - Java backend runs on port **3001**  
   - MySQL runs on port **3306**

2. Changes made in the code editor (local source files) are applied live to the running container for faster development and testing.

---

### Steps

1. Clone the repository

```bash
git clone <your-github-repo-url>
cd <repo-folder>


2. Run the application using Docker Compose
docker-compose up --build

This will start two containers:
Backend app running on port 3001
MySQL database running on port 3306

3. Verify containers are running:
docker ps
Access the API on http://localhost:3001


4. API Endpoints

Authentication
a) POST /api/signup
Register a new user
Request body:
{
  "userId": "user@example.com",
  "password": "Password123!",
  "username": "홍길동"
}

b)POST /api/login
Login with userId and password
Returns JWT token


c)PATCH /api/user
Update user info (password and/or username)
Requires Bearer token in header
Request body (send only fields to update):
{
  "password": "NewPassword123!",
  "username": "김철수"
}


Posts

a)POST /api/posts
Create a post
Request body:
{
  "title": "Post Title",
  "content": "Post content"
}

b)GET /api/posts?page=1
List posts with pagination (max 20 per page)

c)GET /api/posts/{postId}
Get detailed info of a post

Comments

a)POST /api/comments
Create a comment
Request body:

{
  "content": "Comment content"
}

b)GET /api/comments?postId=1&cursor=1
List comments with cursor-based pagination

c)DELETE /api/comments/{commentId}
Delete a comment (only author or post owner)

Login Records & Rankings

a)GET /api/login-records/{userId}
List last 30 login records sorted by most recent

b)GET /api/login-records/weekly-rankings
Get weekly login count rankings (Monday to Sunday)

Notes
All APIs except /signup and /login require Bearer JWT token authentication

Content-Type for all requests and responses: application/json

Stopping the Application
Stop running containers:
docker-compose down

Contact
For any questions, please reach out.

Thank you for reviewing this project!




