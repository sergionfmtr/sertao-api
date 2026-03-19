package com.clinica.sertao_api.consultas;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record ConsultaRequest(
    @Schema(description = "ID do médico que realizará a consulta", example = "1")
    @NotNull(message = "O ID do médico é obrigatório.")
    Long medicoId,

    @Schema(description = "ID do paciente que será consultado", example = "2")
    @NotNull(message = "O ID do paciente é obrigatório.")
    Long pacienteId,

    @Schema(description = "ID da especialidade da consulta", example = "3")
    @NotNull(message = "O ID da especialidade é obrigatório.")
    Long especialidadeId,

    @Schema(description = "Data e horário da consulta", type = "string", example = "2026-10-15T14:30:00")
    @NotNull(message = "A data da consulta é obrigatória.")
    @Future(message = "A data da consulta deve ser no futuro.")
    LocalDateTime dataConsulta,

    @Schema(description = "Status da consulta", example = "AGENDADA")
    ConsultaStatus status
) {
    public ConsultaDTO toConsultaDto() {
        return new ConsultaDTO(null, this.medicoId(), this.pacienteId(), this.especialidadeId(), this.dataConsulta(), this.status() != null ? this.status() : ConsultaStatus.AGENDADA);
    }
}