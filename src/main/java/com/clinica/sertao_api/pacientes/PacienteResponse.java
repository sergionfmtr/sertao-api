package com.clinica.sertao_api.pacientes;

import java.time.LocalDate;

public record PacienteResponse(
    Long id,
    String name,
    String cpf,
    LocalDate birthDate,
    String phone,
    String email
) {
    public static PacienteResponse toResponse(PacienteDTO dto) {
        return new PacienteResponse(
            dto.id(),
            dto.name(),
            dto.cpf(),
            dto.birthDate(),
            dto.phone(),
            dto.email()
        );
    }
}