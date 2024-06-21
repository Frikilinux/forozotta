CREATE TABLE users(
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    active BOOLEAN NOT NULL,

    PRIMARY KEY(id)
);

CREATE TABLE topics(
    id BIGINT NOT NULL AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    message VARCHAR(500) NOT NULL,
    created_at DATETIME NOT NULL,
    modified_at DATETIME NOT NULL,
    author_id BIGINT NOT NULL,
    status BOOLEAN NOT NULL DEFAULT FALSE,

    PRIMARY KEY(id),
    CONSTRAINT fk_topics_author_id FOREIGN KEY(author_id) REFERENCES users(id)
);
