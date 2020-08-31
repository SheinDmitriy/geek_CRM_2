USE geek_crm_db;

DROP TABLE IF EXISTS order_items cascade;

CREATE TABLE order_items
(
    id            INT          NOT NULL,
    product_id INT          NULL,
    quantity      INT NULL,
    price       DECIMAL(10, 2) NULL,
    PRIMARY KEY (id),
    INDEX order_items_products_idx (product_id ASC) VISIBLE,
    CONSTRAINT order_items_products
        FOREIGN KEY (product_id)
            REFERENCES geek_crm_db.products (id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);
