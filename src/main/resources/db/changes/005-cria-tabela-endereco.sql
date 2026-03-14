--liquibase formatted sql
--changeset dev:005-cria-tabela-endereco
CREATE TABLE endereco (
    id_endereco BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_paciente BIGINT NOT NULL,
    cep VARCHAR(9) NOT NULL,
    logradouro VARCHAR(150) NOT NULL,
    numero VARCHAR(10),
    complemento VARCHAR(50),
    bairro VARCHAR(150) NOT NULL,
    cidade VARCHAR(30) NOT NULL,
    estado CHAR(2) NOT NULL,
    CONSTRAINT fk_endereco_paciente FOREIGN KEY (id_paciente) REFERENCES paciente(id_paciente)
);