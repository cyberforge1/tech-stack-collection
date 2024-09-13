-- db-schema.sql

CREATE DATABASE IF NOT EXISTS `tech_stacks_todos`;

USE `tech_stacks_todos`;

CREATE TABLE IF NOT EXISTS todos (
    id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255)
);

SELECT * FROM todos;
