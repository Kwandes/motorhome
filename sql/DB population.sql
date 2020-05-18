USE motorhome;

#-------------------------------------------
-- Table : Employee
#-------------------------------------------

INSERT INTO employee (first_name, last_name, position) VALUES
("Amanda", "Gray", "Bookkeeper"),
("Michael", "Brown", "Sales Assistant"),
("Ulfric", "Stormcloak", "Owner");


#-------------------------------------------
-- Table : Customer
#-------------------------------------------

INSERT INTO customer (first_name, last_name, cpr, birthdate, address) VALUES
("Adele", "Spencer", "160428-1231", "2016-04-28", "Lorem ipsum dolor sit amet, consectetur adipiscing elit"),
("Connor", "Turner", "160601-3121", "2016-06-01", "Lorem ipsum dolor sit amet, consectetur adipiscing elit"),
("Seth", "Ramirez", "200813-5412", "2020-08-13", "Lorem ipsum dolor sit amet, consectetur adipiscing elit");


#-------------------------------------------
-- Table : Phone_Number
#-------------------------------------------

INSERT INTO phone_number ( number, customer_id ) VALUES
("+4542555129", 2000),
("+4525558236", 2000),
("+4571555156", 2001),
("+4531555484", 2002),
("+4540555119", 2002);


#-------------------------------------------
-- Table : RV
#-------------------------------------------

INSERT INTO rv ( brand, model, rv_type, price ) VALUES
("VW", "Transporter", "Family", 12999),
("Mercedes", "Other Transporter", "Camping", 15999),
("Lightning", "McQueen", "Awesome", 167000);


#-------------------------------------------
-- Table : RV_Extra
#-------------------------------------------

INSERT INTO rv_extra ( name, price ) VALUES 
("Child", 99999),
("Seat", 10123),
("Bed Linen", 123),
("Goldfish", 6801);


#-------------------------------------------
-- Table : Rental_Contract
#-------------------------------------------

INSERT INTO rental_contract ( date_start, date_end, base_price ) VALUES
("1990-08-24", "1990-08-24", 399);


#-------------------------------------------
-- Table : Rental_Contract_Has_RV_Extra
#-------------------------------------------

INSERT INTO rental_contract_has_rv_extra (rental_contract_id, rv_extra_id) VALUES 
(5000, 4000),
(5000, 4001),
(5000, 4002);
