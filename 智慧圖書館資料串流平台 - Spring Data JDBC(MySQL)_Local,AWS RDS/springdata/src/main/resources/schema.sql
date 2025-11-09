CREATE DATABASE IF NOT EXISTS project20251007;
USE project20251007;

CREATE TABLE IF NOT EXISTS books(
	id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    description VARCHAR(100)
);

INSERT INTO books(title, description) VALUES('Springboot JDBC', 'first project on SB JDBC')