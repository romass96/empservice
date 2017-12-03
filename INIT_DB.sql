CREATE DATABASE employeeservice;
USE employeeservice;

CREATE TABLE countries (
  id BIGINT(20) AUTO_INCREMENT,
  name VARCHAR(25) NOT NULL ,
  PRIMARY KEY (id)
);

CREATE TABLE regions (
  id BIGINT(20) AUTO_INCREMENT,
  name VARCHAR(25) NOT NULL ,
  country_id BIGINT(20),
  PRIMARY KEY (id),
  FOREIGN KEY (country_id) REFERENCES countries(id)
);

CREATE TABLE employees (
  login VARCHAR(25) NOT NULL ,
  password VARCHAR(45) NOT NULL ,
  firstname VARCHAR(25) NOT NULL ,
  lastname VARCHAR(25),
  PRIMARY KEY (login)
);

CREATE TABLE orders (
  id BIGINT(20) AUTO_INCREMENT,
  employee_login VARCHAR(25) NOT NULL ,
  region_id BIGINT(20) NOT NULL ,
  price INT(11) NOT NULL ,
  order_date DATE NOT NULL ,
  PRIMARY KEY (id),
  FOREIGN KEY (employee_login) REFERENCES employees(login),
  FOREIGN KEY (region_id) REFERENCES regions(id)
);