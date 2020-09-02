USE geek_crm_db;

DROP TABLE IF EXISTS products cascade;

CREATE TABLE products
(
    id          INT            NOT NULL,
    title       VARCHAR(512)   NULL,
    description VARCHAR(4096)  NULL,
    price       DECIMAL(10, 2) NULL,
    PRIMARY KEY (id)
);
