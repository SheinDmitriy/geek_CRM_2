USE geek_crm_db;

DROP TABLE IF EXISTS payment_statuses cascade;

CREATE TABLE payment_statuses
(
    id    INT          NOT NULL,
    title VARCHAR(256) NULL,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS payments cascade;

CREATE TABLE payments
(
    id                INT            NOT NULL,
    order_id          INT            NULL,
    payment_status_id INT            NULL,
    amount            DECIMAL(10, 2) NULL,
    create_date       DATE           NULL,
    complete_date     DATE           NULL,
    PRIMARY KEY (id),
    INDEX payments_orders_idx (order_id ASC) VISIBLE,
    INDEX payments_payment_statuses_idx (payment_status_id ASC) VISIBLE,
    CONSTRAINT payments_orders
        FOREIGN KEY (order_id)
            REFERENCES geek_crm_db.orders (id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT payments_payment_statuses
        FOREIGN KEY (payment_status_id)
            REFERENCES geek_crm_db.payment_statuses (id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);