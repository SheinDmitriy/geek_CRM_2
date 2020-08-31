USE geek_crm_db;

DROP TABLE IF EXISTS departments cascade;

CREATE TABLE departments
(
    id INT NOT NULL AUTO_INCREMENT,
    title VARCHAR(256) NULL,
    manager_id INT,
    PRIMARY KEY (id)
);