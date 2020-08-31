USE geek_crm_db;

DROP TABLE IF EXISTS orders cascade;

CREATE TABLE orders
(
    id                INT          NOT NULL,
    store_id          INT          NULL,
    customer_id       INT          NULL,
    PRIMARY KEY (id),
    INDEX orders_stores_idx (store_id ASC) VISIBLE,
    INDEX orders_customers_idx (customer_id ASC) VISIBLE,
    CONSTRAINT orders_stores
        FOREIGN KEY (store_id)
            REFERENCES geek_crm_db.stores (id)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    CONSTRAINT orders_customers
        FOREIGN KEY (customer_id)
            REFERENCES geek_crm_db.customers (id)
            ON DELETE CASCADE
            ON UPDATE CASCADE
);