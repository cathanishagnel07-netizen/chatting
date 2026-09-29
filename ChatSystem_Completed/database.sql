CREATE DATABASE IF NOT EXISTS db_454pzrx5k;
USE db_454pzrx5k;

CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS messages (
    id INT AUTO_INCREMENT PRIMARY KEY,
    sender VARCHAR(50) NOT NULL,
    receiver VARCHAR(50) NOT NULL,
    message TEXT NOT NULL,
    message_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    INDEX idx_chat (sender, receiver, id),
    INDEX idx_chat_reverse (receiver, sender, id)
);

INSERT INTO users (username,password) VALUES ('user1','1234') ON DUPLICATE KEY UPDATE username=username;
INSERT INTO users (username,password) VALUES ('user2','1234') ON DUPLICATE KEY UPDATE username=username;
