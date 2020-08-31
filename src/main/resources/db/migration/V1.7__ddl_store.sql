USE geek_crm_db;

DROP TABLE IF EXISTS stores cascade;

CREATE TABLE stores
(
    id                INT          NOT NULL,
    title         VARCHAR(256) NULL,
    url        VARCHAR(256) NULL,
    PRIMARY KEY (id)
);
