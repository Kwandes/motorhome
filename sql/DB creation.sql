DROP SCHEMA IF EXISTS motorhome;

CREATE SCHEMA IF NOT EXISTS motorhome;

USE motorhome;

#-------------------------------------------
-- Table : Employee
-- ID    : 1xxx
#-------------------------------------------

-- SELECT * FROM employee;

DROP TABLE IF EXISTS employee;

CREATE TABLE IF NOT EXISTS employee (
id INT NOT NULL AUTO_INCREMENT,
first_name VARCHAR(40) NOT NULL,
last_name VARCHAR(40) NOT NULL,
position VARCHAR(15) NOT NULL,
gender VARCHAR(10) DEFAULT NULL,
PRIMARY KEY(id)
);

ALTER TABLE employee AUTO_INCREMENT = 1000;


#-------------------------------------------
-- Table : customer
-- ID    : 2xxx
#-------------------------------------------

-- SELECT * FROM customer;

DROP TABLE IF EXISTS customer;

CREATE TABLE IF NOT EXISTS customer (
id INT NOT NULL AUTO_INCREMENT,
first_name VARCHAR(40) NOT NULL,
last_name VARCHAR(40) NOT NULL,
cpr VARCHAR(11) NOT NULL UNIQUE,
email VARCHAR(80) DEFAULT NULL,
birthdate DATE NOT NULL,
phone_number VARCHAR(15),
address VARCHAR(255) NOT NULL,
address2 VARCHAR(255) DEFAULT NULL,
gender VARCHAR(10) DEFAULT NULL,
PRIMARY KEY(id)
);

ALTER TABLE customer AUTO_INCREMENT = 2000;


#-------------------------------------------
-- Table : rv
-- ID    : 3xxx
#-------------------------------------------

-- SELECT * FROM rv;

DROP TABLE IF EXISTS rv;

CREATE TABLE IF NOT EXISTS rv (
id INT NOT NULL AUTO_INCREMENT,
brand VARCHAR(30) NOT NULL,
model VARCHAR(30) NOT NULL,
color VARCHAR(30) NOT NULL DEFAULT "white",
fuel_status DECIMAL(2,1) NOT NULL DEFAULT 1.0,
km_driven INT NOT NULL DEFAULT 0,
rv_type VARCHAR(30) NOT NULL,
price INT NOT NULL,
requires_cleaning BOOLEAN NOT NULL DEFAULT 0,
requires_maintenance BOOLEAN NOT NULL DEFAULT 0,
requires_further_service BOOLEAN NOT NULL DEFAULT 0,
is_rented BOOLEAN NOT NULL DEFAULT 0,
PRIMARY KEY(id)
);

ALTER TABLE rv AUTO_INCREMENT = 3000;


#-------------------------------------------
-- Table : rental_contract
-- ID    : 4xxx
#-------------------------------------------

-- SELECT * FROM rental_contract;

DROP TABLE IF EXISTS rental_contract;

CREATE TABLE IF NOT EXISTS rental_contract (
id INT NOT NULL AUTO_INCREMENT,
date_signed DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
date_start DATETIME NOT NULL,
date_end DATETIME NOT NULL,
address_dropoff VARCHAR(255) NOT NULL DEFAULT "Store Address",
address_pickup VARCHAR(255) NOT NULL DEFAULT "Store Address",
base_price INT NOT NULL,
final_price INT DEFAULT 0,
km_driven INT DEFAULT  0,
extras VARCHAR(510),
customer_id INT DEFAULT NULL,
rv_id INT DEFAULT NULL,
employee_id INT DEFAULT NULL,
status VARCHAR(10) DEFAULT "open",
PRIMARY KEY(id),
CONSTRAINT `fk_rental_contract_customer`
	FOREIGN KEY(customer_id)
    REFERENCES motorhome.customer(id)
    ON UPDATE CASCADE
    ON DELETE SET NULL,
CONSTRAINT `fk_rental_contract_rv`
	FOREIGN KEY(rv_id)
    REFERENCES motorhome.rv(id)
    ON UPDATE CASCADE 
    ON DELETE SET NULL,
CONSTRAINT `fk_rental_contract_employee`
	FOREIGN KEY(employee_id)
    REFERENCES motorhome.employee(id)
    ON UPDATE CASCADE
    ON DELETE SET NULL
);

ALTER TABLE rental_contract AUTO_INCREMENT = 4000;