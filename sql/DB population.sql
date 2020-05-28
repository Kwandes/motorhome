USE motorhome;

#-------------------------------------------
-- Table : Employee
#-------------------------------------------

INSERT INTO employee (first_name, last_name, position) VALUES
("Amanda", "Gray", "Bookkeeper"),
("Michael", "Brown", "Sales Assistant"),
("Matilda", "Brown", "Sales Assistant"),
("Elisabeth", "Brown", "Sales Assistant"),
("Joe", "Brown", "Sales Assistant"),
("Jane", "Brown", "Cleaning Staff"),
("Polina", "Brown", "Cleaning Staff"),
("Robert", "Brown", "Auto-mechanic"),
("Lizzy", "Brown", "Owner"),
("Ulfric", "Stormcloak", "Owner");

#-------------------------------------------
-- Table : User
#-------------------------------------------

INSERT INTO user ( username, password, employee_id ) VALUES
("aman_book0", "3673422834", 1000),
("mich_sale1", "9870632412", 1001),
("mati_sale2", "8903472523", 1002),
("elis_sale3", "4578693211", 1003),
("joe#_sale4", "3240987433", 1004),
("jane_clea5", "3450819231", 1005),
("poli_clea6", "7653421231", 1006),
("robe_auto7", "3452546778", 1007),
("lizz_owne8", "6758342145", 1008),
("ulfr_owne9", "7658234125", 1009);

#-------------------------------------------
-- Table : Customer
#-------------------------------------------

INSERT INTO customer (first_name, last_name, cpr, birthdate, address, phone_number) VALUES
("Adele", "Spencer", "160428-1231", "2016-04-28", "Yeetsgade 71", "31333213"),
("Connor", "Turner", "160601-3121", "2016-06-01", "Bobevejen 13", "+4533445513"),
("Seth", "Ramirez", "200813-5412", "2020-08-13", "Bob Buildersgade 89", "45673233");


#-------------------------------------------
-- Table : RV
#-------------------------------------------

INSERT INTO rv ( brand, model, rv_type, price, fuel_status, km_driven, requires_cleaning, requires_maintenance, requires_further_service, is_rented ) VALUES
("VW", "Transporter", "Family", 129, 0.1, 3219, 0, 0, 0, 1),
("Mercedes", "Other Transporter", "Camping", 159, 0.5, 3214123, 1, 0, 1, 0),
("Lightning", "McQueen", "Awesome", 9999, 0.4, 6574, 0, 0, 0, 0),
("Hanomag-Henschel", "Orion", "Big", 909, 0.7, 56732, 0, 1, 0, 0),
("Dodge", "Travco", "Smol", 450, 1.0, 3139, 1, 1, 0, 0),
("GMC", "Motorhome", "Missing", 312, 0.5, 64987, 0, 0, 1, 0),
("HOTEL ?", "TRIVAGO !", "WHEREVER YOU GO", 319, 0.6, 54632, 0, 0, 0, 0),
("Toyota", "HiAce", "Class-A", 231, 0.3, 9790, 1, 0, 0, 0),
("Ford", "F650", "Class-B", 213, 0.2, 1280, 0, 1, 0, 0);


#-------------------------------------------
-- Table : Rental_Contract
#-------------------------------------------

INSERT INTO rental_contract ( date_start, date_end, base_price, rv_id, employee_id, customer_id ) VALUES
("2010-08-14", "2022-11-24", 399, 3000, 1000, 2002),
("1930-08-14", "2000-11-24", 399, 3004, 1001, 2002),
("1980-08-14", "1790-11-24", 399, 3002, 1002, 2001),
("1990-08-24", "2000-08-24", 399, 3003, 1002, 2000);


