package com.clinica.sertao_api.consultas;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ConsultaRequest(
    @NotNull(message = "O ID do médico é obrigatório.")
    Long medicoId,

    @NotNull(message = "O ID do paciente é obrigatório.")
    Long pacienteId,

    @NotNull(message = "A data da consulta é obrigatória.")
    @Future(message = "A data da consulta deve ser no futuro.")
    LocalDateTime dataConsulta
) {
    public ConsultaDTO toConsultaDto() {
        return new ConsultaDTO(null, this.medicoId(), this.pacienteId(), this.dataConsulta());
    }
}