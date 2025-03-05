CREATE TABLE IF NOT EXISTS user
(
    id                INT PRIMARY KEY AUTO_INCREMENT,
    username          VARCHAR(50)  NOT NULL UNIQUE,
    email             VARCHAR(100) NOT NULL UNIQUE,
    registration_date DATETIME     NOT NULL,
    password          VARCHAR(100) NOT NULL
);