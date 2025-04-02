CREATE TABLE users
(
    id         UUID PRIMARY KEY,
    name       VARCHAR(100),
    email      VARCHAR(100) UNIQUE NOT NULL,
    password   VARCHAR(255)        NOT NULL,
    role       VARCHAR(20)         NOT NULL,
    created_at TIMESTAMP           NOT NULL
);