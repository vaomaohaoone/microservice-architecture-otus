CREATE TABLE IF NOT EXISTS otus.users
(
    id           bigint PRIMARY KEY,
    user_name    varchar NOT NULL,
    first_name   varchar NOT NULL,
    last_name    varchar NOT NULL,
    email        varchar NOT NULL,
    phone        varchar
);

ALTER TABLE otus.users ALTER COLUMN id
    ADD GENERATED BY DEFAULT AS IDENTITY