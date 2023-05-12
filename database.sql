DROP DATABASE IF EXISTS cars;
CREATE DATABASE cars;
USE cars;

CREATE TABLE potrebiteli (
	name VARCHAR(255), pass VARCHAR(255), mail VARCHAR(255)
);

CREATE TABLE cars (
	id INT ,
	brand VARCHAR(255) ,
	image VARCHAR(255) ,
	price INT
);

INSERT INTO potrebiteli VALUES
	("admin", "admin", "admin@cars.bg") ,
	("guest", "1234", "guest@cars.bg") ;

INSERT INTO cars VALUES
	(1, "BMW", "bmw.jpg", 5) ,
	(2, "Mercedes", "mercedes.jpg", 7) ;
	
