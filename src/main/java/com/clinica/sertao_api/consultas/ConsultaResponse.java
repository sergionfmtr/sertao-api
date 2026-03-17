package com.clinica.sertao_api.consultas;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record ConsultaResponse(
    @Schema(description = "ID único do agendamento da consulta", example = "1")
    Long id,

    @Schema(description = "ID do médico responsável pela consulta", example = "1")
    Long medicoId,

    @Schema(description = "ID do paciente que será consultado", example = "2")
    Long pacienteId,

    @Schema(description = "ID da especialidade médica da consulta", example = "3")
    Long especialidadeId,

    @Schema(description = "Data e horário agendados para a consulta", type = "string", example = "2026-10-15T14:30:00")
    LocalDateTime dataConsulta
) {
    public static ConsultaResponse toResponse(ConsultaDTO dto) {
        return new ConsultaResponse(
            dto.id(),
            dto.medicoId(),
            dto.pacienteId(),
            dto.especialidadeId(),
            dto.dataConsulta()
        );
    }
}