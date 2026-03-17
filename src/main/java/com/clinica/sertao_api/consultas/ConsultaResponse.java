package com.clinica.sertao_api.consultas;

import java.time.LocalDateTime;

public record ConsultaResponse(
    Long id,
    Long medicoId,
    Long pacienteId,
    LocalDateTime dataConsulta
) {
    public static ConsultaResponse toResponse(ConsultaDTO dto) {
        return new ConsultaResponse(
            dto.id(),
            dto.medicoId(),
            dto.pacienteId(),
            dto.dataConsulta()
        );
    }
}