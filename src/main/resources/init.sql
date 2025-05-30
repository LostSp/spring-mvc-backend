-- init.sql

CREATE DATABASE IF NOT EXISTS springappdb;

USE springappdb;

CREATE TABLE IF NOT EXISTS members (
    id VARCHAR(255) PRIMARY KEY,       -- corresponds to email, used as ID
    password VARCHAR(255) NOT NULL,    -- hashed password
    username VARCHAR(100) NOT NULL,    -- Korean username
    created_at DATETIME          -- registrationTime
);


CREATE TABLE IF NOT EXISTS posts(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    content TEXT NOT NULL,
    username VARCHAR(100),             -- for response convenience
    created_at DATETIME,
    user_id VARCHAR(255),              -- foreign key referencing member(id)
    FOREIGN KEY (user_id) REFERENCES members(id)
);

CREATE TABLE IF NOT EXISTS comments (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    post_id BIGINT NOT NULL,
    user_id VARCHAR(255) NOT NULL,
    username VARCHAR(100),
    content VARCHAR(500) NOT NULL,
    created_at DATETIME,
    FOREIGN KEY (post_id) REFERENCES posts(id) ,
    FOREIGN KEY (user_id) REFERENCES members(id)
);

CREATE TABLE IF NOT EXISTS user_logins (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id VARCHAR(255),
    login_time DATETIME ,
    ip_address VARCHAR(45),
    FOREIGN KEY (user_id) REFERENCES members(id) ON DELETE SET NULL
);

