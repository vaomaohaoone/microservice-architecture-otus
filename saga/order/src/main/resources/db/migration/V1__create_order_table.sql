CREATE TABLE IF NOT EXISTS otus.order
(
    id             bigint PRIMARY KEY,
    user_name      varchar        NOT NULL UNIQUE,
    sum_order      numeric(10, 2) NOT NULL,
    e_mail         varchar        NULL,
    status         varchar        NOT NULL,
    product_id     varchar        NOT NULL,
    product_amount int        NOT NULL
)