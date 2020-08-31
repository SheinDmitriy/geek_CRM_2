USE geek_crm_db;

# Address parts for normalisation

DROP TABLE IF EXISTS short_addresses cascade;

CREATE TABLE short_addresses
(
    id      INT           NOT NULL,
    address VARCHAR(4096) NULL,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS cities cascade;

CREATE TABLE cities
(
    id    INT          NOT NULL,
    title VARCHAR(256) NULL,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS regions cascade;

CREATE TABLE regions
(
    id    INT          NOT NULL,
    title VARCHAR(256) NULL,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS countries cascade;

CREATE TABLE countries
(
    id    INT          NOT NULL,
    title VARCHAR(256) NULL,
    PRIMARY KEY (id)
);

# Full address table

DROP TABLE IF EXISTS addresses;

CREATE TABLE addresses
(
    id               INT NOT NULL,
    country_id       INT NULL,
    region_id        INT NULL,
    city_id         INT NULL,
    short_address_id INT NULL,
    PRIMARY KEY (id),
    INDEX addresses_countries_idx (country_id ASC) VISIBLE,
    INDEX addresses_regions_idx (region_id ASC) VISIBLE,
    INDEX addresses_cities_idx (city_id ASC) VISIBLE,
    INDEX addresses_short_addresses_idx (short_address_id ASC) VISIBLE,
    CONSTRAINT addresses_countries
        FOREIGN KEY (country_id)
            REFERENCES geek_crm_db.countries (id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT addresses_regions
        FOREIGN KEY (region_id)
            REFERENCES geek_crm_db.regions (id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT addresses_cities
        FOREIGN KEY (city_id)
            REFERENCES geek_crm_db.cities (id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT addresses_short_addresses
        FOREIGN KEY (short_address_id)
            REFERENCES geek_crm_db.short_addresses (id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);







