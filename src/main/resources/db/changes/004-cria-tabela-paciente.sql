--liquibase formatted sql
--changeset dev:004-cria-tabela-paciente
CREATE TABLE paciente (
    id_paciente BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    cpf VARCHAR(14) NOT NULL UNIQUE,
    data_nascimento DATE NOT NULL,
    telefone VARCHAR(18),
    email VARCHAR(100)
);