CREATE TABLE users (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(255) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       name VARCHAR(255) NOT NULL,
                       dob DATE NOT NULL
);
CREATE TABLE addresses (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           street VARCHAR(255) NOT NULL,
                           city VARCHAR(255) NOT NULL,
                           state VARCHAR(255) NOT NULL,
                           postal_code VARCHAR(20) NOT NULL,
                           user_id BIGINT,
                           FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

