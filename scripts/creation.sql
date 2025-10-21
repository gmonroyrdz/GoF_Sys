-- Creación de la estructura de tablas para la base de datos Escuela
DROP DATABASE IF EXISTS escuela;
CREATE DATABASE escuela;
USE escuela;
CREATE TABLE departamento(
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(100),
    direccion VARCHAR(200)
);

CREATE TABLE empleado(
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(100),
    apellido_paterno VARCHAR(100),
    apellido_materno VARCHAR(100),
    edad INT,
    departamento_id INT,
    FOREIGN KEY(departamento_id) REFERENCES departamento(id)
);

