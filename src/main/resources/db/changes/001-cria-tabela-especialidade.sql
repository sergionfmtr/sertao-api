--liquibase formatted sql
--changeset dev:001-cria-tabela-especialidade
CREATE TABLE especialidade (
    id_especialidade BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL
);