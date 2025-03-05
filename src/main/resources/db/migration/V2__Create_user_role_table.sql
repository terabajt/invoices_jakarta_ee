CREATE TABLE IF NOT EXISTS user_role (
    username VARCHAR(50) NOT NULL,
    role_name VARCHAR(20) NOT NULL DEFAULT 'USER',
    PRIMARY KEY (username, role_name),
    FOREIGN KEY (username) REFERENCES user(username)
    );