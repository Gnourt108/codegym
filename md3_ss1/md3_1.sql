create database student_management;

use `student_management`;

create table Class (
	id int primary key auto_increment,
    name varchar(50) not null    
);

create table Teacher(
	id int primary key auto_increment,
    name varchar(100) not null,
    age int,
    country varchar(50)
);

show tables;

describe Class;
describe Teacher;

insert into Class (name) values
('C01'),
('C02'),
('C03');

INSERT INTO Teacher (name, age, country) VALUES 
('Nguyễn Văn A', 35, 'Việt Nam'),
('Trần Thị B', 28, 'Việt Nam'),
('John Smith', 42, 'USA');

SELECT * FROM Class;
SELECT * FROM Teacher;

