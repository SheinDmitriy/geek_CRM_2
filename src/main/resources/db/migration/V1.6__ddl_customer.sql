USE geek_crm_db;

DROP TABLE IF EXISTS customers cascade;

CREATE TABLE customers
(
    id                INT          NOT NULL,
    nick_name         VARCHAR(256) NULL,
    first_name        VARCHAR(256) NULL,
    last_name         VARCHAR(256) NULL,
    phone             VARCHAR(45)  NULL,
    email             VARCHAR(128) NULL,
    birth_date        DATE         NULL,
    registration_date DATE         NULL,
    address_id        INT          NULL,
    PRIMARY KEY (id),
    INDEX customers_addresses_idx (address_id ASC) VISIBLE,
    CONSTRAINT customers_addresses
        FOREIGN KEY (address_id)
            REFERENCES geek_crm_db.addresses (id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);
