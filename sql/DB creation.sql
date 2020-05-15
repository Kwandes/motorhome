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
gender VARCHAR(10),
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
email VARCHAR(80),
birthdate DATE NOT NULL,
address VARCHAR(255) NOT NULL,
address2 VARCHAR(255),
gender VARCHAR(10),
PRIMARY KEY(id)
);

ALTER TABLE customer AUTO_INCREMENT = 2000;


#-------------------------------------------
-- Table : phone_number
#-------------------------------------------

-- SELECT * FROM phone_number;

DROP TABLE IF EXISTS phone_number;

CREATE TABLE IF NOT EXISTS phone_number (
number VARCHAR(15) NOT NULL,
customer_id INT NOT NULL,
PRIMARY KEY ( number, customer_id ),
CONSTRAINT `fk_customer_phone_number`
	FOREIGN KEY(customer_id)
	REFERENCES motorhome.customer(id)
    ON UPDATE CASCADE
    ON DELETE CASCADE
);


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
color VARCHAR(30) NOT NULL,
fuel_status DECIMAL(2,1) NOT NULL,
km_driven INT NOT NULL,
rv_type VARCHAR(30) NOT NULL,
price INT NOT NULL,
requires_cleaning BOOLEAN NOT NULL,
requires_maintenance BOOLEAN NOT NULL,
requires_further_service BOOLEAN NOT NULL,
is_rented BOOLEAN NOT NULL,
PRIMARY KEY(id)
);

ALTER TABLE rv AUTO_INCREMENT = 3000;


#-------------------------------------------
-- Table : rv_extra
-- ID    : 4xxx
#-------------------------------------------

-- SELECT * FROM rv_extra;

DROP TABLE IF EXISTS rv_extra;

CREATE TABLE IF NOT EXISTS rv_extra (
id INT NOT NULL AUTO_INCREMENT,
name VARCHAR(40) NOT NULL,
price INT NOT NULL,
PRIMARY KEY(id)
);

ALTER TABLE rv_extra AUTO_INCREMENT = 4000;


#-------------------------------------------
-- Table : rental_contract
-- ID    : 5xxx
#-------------------------------------------

-- SELECT * FROM rental_contract;

DROP TABLE IF EXISTS rental_contract;

CREATE TABLE IF NOT EXISTS rental_contract (
id INT NOT NULL,
date_signed DATETIME NOT NULL,
date_start DATETIME NOT NULL,
date_end DATETIME NOT NULL,
address_dropoff VARCHAR(255) NOT NULL,
address_pickup VARCHAR(255) NOT NULL,
base_price INT NOT NULL,
final_price INT,
km_driven INT,
customer_id INT,
rv_id INT,
employee_id INT,
status VARCHAR(10) NOT NULL,
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

ALTER TABLE rental_contract AUTO_INCREMENT = 5000;


#-------------------------------------------
-- Table : rental_contract_has_rv_extra
#-------------------------------------------

-- SELECT * FROM rental_contract_has_rv_extra;

DROP TABLE IF EXISTS rental_contract_has_rv_extra;

CREATE TABLE IF NOT EXISTS rental_contract_has_rv_extra (
rental_contract_id INT NOT NULL,
rv_extra_id INT NOT NULL,
amount INT NOT NULL,
PRIMARY KEY(rental_contract_id, rv_extra_id),
CONSTRAINT `fk_rental_contract_rv_extra1`
	FOREIGN KEY(rental_contract_id)
    REFERENCES motorhome.rental_contract(id)
    ON UPDATE CASCADE
    ON DELETE CASCADE,
CONSTRAINT `fk_rental_contract_rv_extra2`
	FOREIGN KEY(rv_extra_id)
    REFERENCES motorhome.rv_extra(id)
    ON UPDATE CASCADE
    ON DELETE CASCADE
);