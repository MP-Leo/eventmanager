CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS users
(
    user_id    UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name       VARCHAR(100),
    email      VARCHAR(100) UNIQUE NOT NULL,
    password   VARCHAR(255)        NOT NULL,
    role       VARCHAR(20)         NOT NULL,
    created_at TIMESTAMP           NOT NULL
);

CREATE TABLE IF NOT EXISTS events
(
    event_id     UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    title        VARCHAR(100) NOT NULL,
    description  VARCHAR(150),
    event_date   TIMESTAMP    NOT NULL,
    created_at   TIMESTAMP    NOT NULL,
    status       VARCHAR(20)  NOT NULL,
    capacity     INT          NOT NULL,
    creator_id   UUID         NOT NULL,
    creator_name VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS subscriptions
(
    subscription_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    user_id         UUID        NOT NULL,
    event_id        UUID        NOT NULL,
    subscribed_at   TIMESTAMP   NOT NULL,
    status          VARCHAR(20) NOT NULL
);