package com.clinica.sertao_api.pacientes;

import java.time.LocalDate;

public record PacienteDTO(
        Long id,
        String name,
        String cpf,
        LocalDate birthDate,
        String phone,
        String email
) {
    public PacienteDTO(Paciente paciente) {
        this(
            paciente.getId(),
            paciente.getNome(),
            paciente.getCpf(),
            paciente.getDataNascimento(),
            paciente.getTelefone(),
            paciente.getEmail()
        );
    }
}