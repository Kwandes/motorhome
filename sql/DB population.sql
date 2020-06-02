USE motorhome;

#-------------------------------------------
-- Table : Employee
#-------------------------------------------

INSERT INTO employee (first_name, last_name, position) VALUES
("Amanda", "Gray", "Bookkeeper"),
("Michael", "Hayes", "Sales Assistant"),
("Matilda", "Lopez", "Sales Assistant"),
("Elisabeth", "Ramos", "Sales Assistant"),
("Joe", "Lawrence", "Sales Assistant"),
("Jane", "Murray", "Cleaning Staff"),
("Polina", "Brown", "Cleaning Staff"),
("Robert", "Porter", "Auto-mechanic"),
("Lizzy", "Brown", "Owner"),
("Peter", "Simmons", "Owner");

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
("pete_owne9", "7658234125", 1009);

#-------------------------------------------
-- Table : Customer
#-------------------------------------------

INSERT INTO customer (first_name, last_name, cpr, birthdate, address, phone_number) VALUES
("Adele", "Spencer", "110987-4356", "1987-09-11", "Brorup, Sondergade 56", "+45-815-557-19"),
("Rosa", "Leandros", "041287-3422", "1987-12-04", "Gistrup, Christianslundsvej 72", "+45-815-553-56"),
("Tamara", "Hyacinth", "101088-7658", "1988-10-10", "Marslet, Lille Vibyvej 38", "+45-305-559-24"),
("Betania", "Milumir", "141292-4532", "1992-12-14", "Frederiksberg C, Lundsbjergvej 71", "+45-305-552-15"),
("Indrajit", "Oghenekevwe", "130594-5678", "1994-05-13", "Kjellerup, Tvaergyden 4", "+45-615-554-38"),
("Gustavo", "Varvara", "151196-3645", "1996-11-15", "Karlslunde, Plouggardsvej 90", "+45-515-555-47"),
("Hermanus", "Willabert", "090197-0897", "1997-01-09", "Kobenhavn K, Sandagervej 52", "+45-515-550-16"),
("Diana", "Ernust", "051098-6787", "1998-10-05", "Frederiksberg C, Adalen 38", "++45-815-556-80"),
("Seth", "Ramirez", "240700-5412", "2000-07-24", "Kobenhavn SV, Handværkervej 42", "+45-715-558-18");


#-------------------------------------------
-- Table : RV
#-------------------------------------------

INSERT INTO rv ( brand, model, rv_type, price, fuel_status, km_driven, requires_cleaning, requires_maintenance, requires_further_service, is_rented ) VALUES
("VW", "Autosleeper Clubman GL", "Campervan", 149, 0.1, 3219, 0, 0, 0, 1), -- rented
("Ford", "E-Series", "Campervan Class C", 159, 0.5, 4599, 0, 0, 0, 1), -- rented
("Hanomag-Henschel", "Orion", "Class C", 133, 0.7, 5672, 0, 0, 0, 1), -- rented
("Dodge", "Travco", "Class C", 240, 1.0, 3139, 0, 0, 0, 1), -- rented
("GMC", "Motorhome", "Class C ", 319, 0.5, 64987, 0, 0, 1, 0), -- maintenance
("Citroen", "H-Van", "Camper", 319, 0.6, 54632, 0, 0, 0, 0), -- available
("Toyota", "HiAce", "Class-A", 231, 0.3, 9790, 1, 0, 0, 0), -- maintenance
("American Coach", "Dream", "Class-A", 231, 1.0, 900, 1, 1, 0, 0), -- maintenance
("Entegra Coach", "Odyssey", "Class-C", 865, 0.3, 9790, 1, 1, 0, 0), -- maintenance
("Entegra Coach", "Esteem", "Class-C", 431, 0.3, 9790, 1, 0, 1, 0), -- maintenance
("Entegra Coach", "Qwest", "Class-C", 311, 0.3, 9790, 0, 0, 0, 0), -- available
("Entegra Coach", "Accolade", "Class-C", 100, 0.3, 9790, 0, 0, 0, 0), -- available
("Entegra Coach", "Vision", "Class-A", 246, 0.3, 9790, 1, 1, 0, 0), -- maintenance
("Entegra Coach", "Vision XL", "Class-A", 231, 0.3, 9790, 0, 1, 1, 0), -- maintenance
("Entegra Coach", "Emblem", "Class-A", 321, 0.3, 9790, 1, 0, 1, 0), -- maintenance
("Entegra Coach", "Reatta", "Luxury", 500, 0.3, 9790, 0, 0, 1, 0), -- maintenance
("Entegra Coach", "Aspire", "Luxury", 499, 0.3, 9790, 0, 0, 0, 0), -- available
("Entegra Coach", "Anthem", "Luxury", 355, 0.3, 9790, 0, 0, 0, 0), -- available 
("Entegra Coach", "Cornerstone", "Luxury", 122, 0.3, 9790, 1, 0, 1, 0), -- maintenance
("Jayco", "Alante", "Class-A", 111, 0.3, 9790, 0, 0, 0, 0), -- available
("Jayco", "Precept", "Class-A", 533, 0.3, 9790, 1, 0, 0, 0), -- maintenance
("Jayco", "Embark", "Class-A", 233, 0.3, 9790, 1, 0, 0, 0), -- maintenance
("Tiffin", "Wayfarer", "Class-C", 231, 0.3, 9790, 1, 0, 0, 0), -- maintenance
("Tiffin", "Allegro Breeze", "Class-A", 122, 0.3, 9790, 1, 0, 0, 0), -- maintenance
("Tiffin", "Phaeton", "Class-A", 766, 0.3, 9790, 0, 0, 0, 0), -- available
("Tiffin", "Zephyr", "Class-A", 532, 0.3, 9790, 0, 0, 0, 0), -- available
("Fleetwood", "Irok", "Class-B", 866, 0.3, 9790, 1, 0, 0, 0), -- maintenance
("Coachmen", "Mirada", "Class-A", 922, 0.3, 9790, 0, 0, 0, 0),  -- available
("Coachmen", "Pursuit", "Class-A", 411, 0.3, 9790, 1, 0, 0, 0), -- maintenance
("Coachmen", "Encore", "Class-A", 977, 0.3, 9790, 1, 0, 0, 0), -- maintenance
("Lightning", "McQueen", "Awesome", 9999, 0.4, 6574, 1, 1, 0, 0), -- maintenance
("Ford", "F650", "Class-B", 213, 0.2, 1280, 1, 1, 0, 0); -- maintenance


#-------------------------------------------
-- Table : Rental_Contract
#-------------------------------------------

INSERT INTO rental_contract ( date_start, date_end, base_price, rv_id, employee_id, customer_id ) VALUES
("2020-03-14", "2020-08-14", 1999, 3000, 1000, 2000),
("2020-05-10", "2019-07-29", 1999, 3001, 1001, 2001),
("2020-05-24", "2019-06-30", 1999, 3002, 1002, 2002),
("2020-06-05", "2019-07-13", 1999, 3003, 1003, 2003);


