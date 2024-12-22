create database inventaire;

use inventaire;

create table Product
(
    id       int primary key auto_increment,
    name     varchar(255) unique,
    category varchar(255),
    quantity int,
    price    decimal
);
