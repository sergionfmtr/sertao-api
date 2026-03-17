package com.clinica.sertao_api.consultas;

import com.clinica.sertao_api.especialidades.EspecialidadeResponse;
import com.clinica.sertao_api.medicos.MedicoResponse;
import com.clinica.sertao_api.pacientes.PacienteResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record ConsultaResponse(
    @Schema(description = "ID único do agendamento da consulta", example = "1")
    Long id,

    @Schema(description = "Médico responsável pela consulta")
    MedicoResponse medico,

    @Schema(description = "Paciente que será consultado")
    PacienteResponse paciente,

    @Schema(description = "Especialidade médica da consulta")
    EspecialidadeResponse especialidade,

    @Schema(description = "Data e horário agendados para a consulta", type = "string", example = "2026-10-15T14:30:00")
    LocalDateTime dataConsulta
) {
    public static ConsultaResponse toResponse(ConsultaDTO dto) {
        if (dto == null) return null;
        return new ConsultaResponse(
            dto.id(),
            dto.medico() != null ? MedicoResponse.toResponse(dto.medico()) : null,
            dto.paciente() != null ? PacienteResponse.toResponse(dto.paciente()) : null,
            dto.especialidade() != null ? EspecialidadeResponse.toResponse(dto.especialidade()) : null,
            dto.dataConsulta()
        );
    }
}