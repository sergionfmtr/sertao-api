--liquibase formatted sql
--changeset dev:006-cria-tabela-consultas
CREATE TABLE consultas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    paciente_id BIGINT NOT NULL,
    medico_id BIGINT NOT NULL,
    data_hora_consulta DATETIME NOT NULL,
    status VARCHAR(50),
    CONSTRAINT fk_consultas_paciente FOREIGN KEY (paciente_id) REFERENCES paciente(id_paciente),
    CONSTRAINT fk_consultas_medico FOREIGN KEY (medico_id) REFERENCES medico(id_medico)
);