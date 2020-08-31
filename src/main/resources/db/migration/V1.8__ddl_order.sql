USE geek_crm_db;

DROP TABLE IF EXISTS order_statuses cascade;

CREATE TABLE order_statuses
(
    id    INT          NOT NULL,
    title VARCHAR(256) NULL,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS orders cascade;

CREATE TABLE orders
(
    id              INT NOT NULL,
    store_id        INT NULL,
    customer_id     INT NULL,
    order_status_id INT NULL,
    PRIMARY KEY (id),
    INDEX orders_stores_idx (store_id ASC) VISIBLE,
    INDEX orders_customers_idx (customer_id ASC) VISIBLE,
    INDEX orders_order_statuses_idx (order_status_id ASC) VISIBLE,
    CONSTRAINT orders_stores
        FOREIGN KEY (store_id)
            REFERENCES geek_crm_db.stores (id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT orders_customers
        FOREIGN KEY (customer_id)
            REFERENCES geek_crm_db.customers (id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT orders_order_statuses
        FOREIGN KEY (order_status_id)
            REFERENCES geek_crm_db.order_statuses (id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);