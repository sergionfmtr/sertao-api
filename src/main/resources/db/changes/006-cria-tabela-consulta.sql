--liquibase formatted sql
--changeset dev:006-cria-tabela-consultas
CREATE TABLE consulta (
    id_consulta BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_paciente BIGINT NOT NULL,
    id_medico BIGINT NOT NULL,
    id_especialidade BIGINT NOT NULL,
    data_hora_consulta DATETIME NOT NULL,
    status VARCHAR(50),
    CONSTRAINT fk_consultas_paciente FOREIGN KEY (id_paciente) REFERENCES paciente(id_paciente),
    CONSTRAINT fk_consultas_medico FOREIGN KEY (id_medico) REFERENCES medico(id_medico),
    CONSTRAINT fk_consultas_especialidade FOREIGN KEY (id_especialidade) REFERENCES especialidade(id_especialidade)
);