USE geek_crm_db;

DROP TABLE IF EXISTS deal_statuses cascade;

CREATE TABLE deal_statuses
(
    id    INT          NOT NULL,
    title VARCHAR(256) NULL,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS deals cascade;

CREATE TABLE deals
(
    id             INT NOT NULL,
    order_id       INT NULL,
    staff_id       INT NULL,
    deal_status_id INT NULL,
    PRIMARY KEY (id),
    INDEX deals_orders_idx (order_id ASC) VISIBLE,
    INDEX deals_staff_idx (staff_id ASC) VISIBLE,
    INDEX deals_deal_statuses_idx (deal_status_id ASC) VISIBLE,
    CONSTRAINT deals_orders
        FOREIGN KEY (order_id)
            REFERENCES geek_crm_db.orders (id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT deals_staff
        FOREIGN KEY (staff_id)
            REFERENCES geek_crm_db.staff (id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT deals_deal_statuses
        FOREIGN KEY (deal_status_id)
            REFERENCES geek_crm_db.deal_statuses (id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);