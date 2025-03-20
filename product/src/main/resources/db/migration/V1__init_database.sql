CREATE TABLE IF NOT EXISTS category (
    id INTEGER PRIMARY KEY NOT NULL ,
    description VARCHAR(255) NOT NULL ,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS product
(
    id INTEGER PRIMARY KEY NOT NULL,
    description VARCHAR(255) NOT NULL ,
    name VARCHAR(255) NOT NULL,
    available_quantity DOUBLE PRECISION NOT NULL,
    price NUMERIC(38, 2),
    category_id INTEGER constraint category_constraint REFERENCES category

);

CREATE SEQUENCE IF NOT EXISTS category_seq INCREMENT BY 50;
CREATE SEQUENCE IF NOT EXISTS product_seq INCREMENT BY 50;
