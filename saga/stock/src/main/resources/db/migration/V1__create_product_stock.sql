CREATE TABLE IF NOT EXISTS otus.product_stock
(
    id             uuid PRIMARY KEY,
    product_id     varchar NOT NULL UNIQUE,
    product_amount int     NOT NULL
)