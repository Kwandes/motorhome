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
-- Table : RV
#-------------------------------------------

INSERT INTO rv ( brand, model, rv_type, price, fuel_status, km_driven, requires_cleaning, requires_maintenance, requires_further_service, is_rented ) VALUES
("VW", "Transporter", "Family", 12999, 0.1, 321, 1, 0, 1, 0),
("Mercedes", "Other Transporter", "Camping", 15999, 0.5, 3214123, 0, 0, 0, 1),
("Lightning", "McQueen", "Awesome", 167000, 0.4, 6574, 1, 1, 1, 0),
("Hanomag-Henschel", "Orion", "Big", 90900, 0.7, 56732, 0, 1, 1, 0),
("Dodge", "Travco", "Smol", 45001, 1.0, 31314, 1, 1, 0, 0),
("GMC", "Motorhome", "Missing", 31245, 0.5, 6423, 0, 0, 1, 0),
("HOTEL ?", "TRIVAGO !", "WHEREVER YOU GO", 31231, 0.6, 54632, 0, 1, 0, 0),
("Toyota", "HiAce", "Class-A", 23121, 0.3, 9754, 1, 1, 0, 0),
("Ford", "F650", "Class-B", 213132, 0.2, 1231, 1, 1, 1, 1);


#-------------------------------------------
-- Table : Rental_Contract
#-------------------------------------------

INSERT INTO rental_contract ( date_start, date_end, base_price ) VALUES
("1990-08-24", "1990-08-24", 399);


