INSERT INTO users (id, name, email, password, role, created_at)
VALUES (RANDOM_UUID(),
        'User',
        'user@email.com',
        '$2a$10$euimDfAJ8aQUWuokeY1Tseyn6Aoxe7o/hzA/7fejMeSKJf2LlS0Lq', --testpassword
        'USER',
        CURRENT_TIMESTAMP);
