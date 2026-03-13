--liquibase formatted sql
--changeset unifametro:1
CREATE TABLE especialidade (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL
);