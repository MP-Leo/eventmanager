INSERT INTO users (user_id, name, email, password, role, created_at)
VALUES ('11111111-1111-1111-1111-111111111111',
        'User',
        'user@email.com',
        '$2a$10$euimDfAJ8aQUWuokeY1Tseyn6Aoxe7o/hzA/7fejMeSKJf2LlS0Lq', --testpassword
        'ADMIN',
        CURRENT_TIMESTAMP)
ON CONFLICT DO NOTHING;


