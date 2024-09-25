CREATE TABLE customers
(
    id           SERIAL PRIMARY KEY,
    name         VARCHAR(20) NOT NULL,
    surname      VARCHAR(30) NOT NULL,
    age          INT,
    phone_number VARCHAR(20)
);

create table orders
(
    id           SERIAL PRIMARY KEY,
    date         DATE,
    customer_id  INT          not null,
    product_name VARCHAR(255) not null,
    amount       DECIMAL,
    FOREIGN KEY (customer_id) REFERENCES customers (id)
);