-- cloud-db-schema.sql

CREATE DATABASE IF NOT EXISTS `tech-stacks-todos`;

USE `tech-stacks-todos`;

CREATE TABLE IF NOT EXISTS todos (
    id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255)
);

SELECT * FROM todos;

SHOW DATABASES;
