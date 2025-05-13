CREATE TABLE schedule (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    todo VARCHAR(100) NOT NULL,
    writer VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    created_date DATETIME NOT NULL,
    modified_date DATETIME NOT NULL
);