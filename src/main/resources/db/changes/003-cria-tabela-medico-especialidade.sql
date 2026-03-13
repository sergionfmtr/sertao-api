--liquibase formatted sql
--changeset dev:003-cria-tabela-medico-especialidade
CREATE TABLE medico_especialidade (
    id_medico BIGINT,
    id_especialidade BIGINT,
    PRIMARY KEY (id_medico, id_especialidade),
    FOREIGN KEY (id_medico) REFERENCES medico(id_medico),
    FOREIGN KEY (id_especialidade) REFERENCES especialidade(id_especialidade)
);