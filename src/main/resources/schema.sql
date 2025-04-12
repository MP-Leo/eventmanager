CREATE TABLE users
(
    user_id    UUID PRIMARY KEY,
    name       VARCHAR(100),
    email      VARCHAR(100) UNIQUE NOT NULL,
    password   VARCHAR(255)        NOT NULL,
    role       VARCHAR(20)         NOT NULL,
    created_at TIMESTAMP           NOT NULL
);

CREATE TABLE events
(
    event_id     UUID DEFAULT RANDOM_UUID() PRIMARY KEY,
    title        VARCHAR(100) NOT NULL,
    description  VARCHAR(150),
    event_date   TIMESTAMP    NOT NULL,
    created_at   TIMESTAMP    NOT NULL,
    status       VARCHAR(20)  NOT NULL,
    capacity     INT          NOT NULL,
    creator_id   UUID         NOT NULL,
    creator_name VARCHAR(100) NOT NULL
);

CREATE TABLE subscriptions
(
    subscription_id            UUID DEFAULT RANDOM_UUID() PRIMARY KEY,
    user_id       UUID         NOT NULL,
    event_id      UUID         NOT NULL,
    subscribed_at TIMESTAMP    NOT NULL,
    status        VARCHAR(20)  NOT NULL
);