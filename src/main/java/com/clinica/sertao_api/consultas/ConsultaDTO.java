package com.clinica.sertao_api.consultas;

import java.time.LocalDateTime;

public record ConsultaDTO(
    Long id,
    Long medicoId,
    Long pacienteId,
    Long especialidadeId,
    LocalDateTime dataConsulta
) {}