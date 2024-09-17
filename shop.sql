create database shop;
use shop;

create table employee(
	employeeId int PRIMARY KEY,
	name varchar(100) not null,
	password varchar (100) not null

);

INSERT INTO employee (employeeId, name, password) VALUES (1,'Empleado1', 'contraseña1');
INSERT INTO employee (employeeId, name, password) VALUES (2,'Empleado2', 'contraseña2');
