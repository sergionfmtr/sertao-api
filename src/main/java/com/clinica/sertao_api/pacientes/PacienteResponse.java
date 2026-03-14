package com.clinica.sertao_api.pacientes;

import java.time.LocalDate;

import com.clinica.sertao_api.pacientes.enderecos.EnderecoDTO;

public record PacienteResponse(
    Long id,
    String name,
    String cpf,
    LocalDate birthDate,
    String phone,
    String email,
    EnderecoDTO endereco
) {
    public static PacienteResponse toResponse(PacienteDTO dto) {
        return new PacienteResponse(
            dto.id(),
            dto.name(),
            dto.cpf(),
            dto.birthDate(),
            dto.phone(),
            dto.email(),
            dto.endereco()
        );
    }
}