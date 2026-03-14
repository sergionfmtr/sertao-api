--liquibase formatted sql
--changeset dev:002-cria-tabela-medico
CREATE TABLE medico (
    id_medico BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    crm VARCHAR(20) NOT NULL,
    telefone VARCHAR(20),
    email VARCHAR(100) UNIQUE
);