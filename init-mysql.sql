CREATE DATABASE IF NOT EXISTS mydb;

GRANT ALL PRIVILEGES ON mydb.* TO 'myuser'@'%' IDENTIFIED BY 'mysql';

GRANT ALL PRIVILEGES ON mydb.* TO 'myuser'@'localhost' IDENTIFIED BY 'mysql';

USE mydb;

CREATE TABLE IF NOT EXISTS user_type (user_type_id INT NOT NULL AUTO_INCREMENT, type VARCHAR(100), description VARCHAR(100), PRIMARY KEY (user_type_id));

CREATE TABLE IF NOT EXISTS user (user_id INT NOT NULL AUTO_INCREMENT, first_name VARCHAR(100), middle_name VARCHAR(100), last_name VARCHAR(100), email_id VARCHAR(100), password VARCHAR(100), city VARCHAR(100), country VARCHAR(100), user_type_id INT, PRIMARY KEY (user_id), CONSTRAINT FK_user_type FOREIGN KEY (user_type_id) REFERENCES user_type(user_type_id));

INSERT INTO user_type (type, description) 
SELECT 'broker', 'This is a broker type of user' FROM DUAL 
WHERE NOT EXISTS 
(SELECT type FROM user_type WHERE type = 'broker');

INSERT INTO user_type (type, description) 
SELECT 'owner', 'This is a owner type of user' FROM DUAL 
WHERE NOT EXISTS 
(SELECT type FROM user_type WHERE type = 'owner');
