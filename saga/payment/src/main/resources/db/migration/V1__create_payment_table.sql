CREATE TABLE IF NOT EXISTS otus.payment
(
    id      uuid PRIMARY KEY,
    user_id varchar        NOT NULL UNIQUE,
    sum     numeric(10, 2) NULL
)