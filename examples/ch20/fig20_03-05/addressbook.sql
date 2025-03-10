DROP TABLE IF EXISTS addresses;

CREATE TABLE addresses
(
	id INTEGER PRIMARY KEY AUTOINCREMENT,
	first VARCHAR (50) NOT NULL,
	last VARCHAR (50) NOT NULL,
	email VARCHAR (254) NOT NULL,
	phone VARCHAR (15) NOT NULL
);

INSERT INTO addresses (first,last,email,phone)
	VALUES ('Anastasia','Popov','demo1@deitel.com','555-5555'),
	('Mina','Gamal','demo2@deitel.com','555-1234');