CREATE TABLE IF NOT EXISTS otus.courier
(
    id             uuid PRIMARY KEY,
    courier_number varchar NOT NULL UNIQUE,
    orderId        int UNIQUE
)