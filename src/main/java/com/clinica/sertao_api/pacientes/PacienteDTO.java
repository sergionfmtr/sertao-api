package com.clinica.sertao_api.pacientes;

import java.time.LocalDate;

import com.clinica.sertao_api.pacientes.enderecos.EnderecoDTO;

public record PacienteDTO(
        Long id,
        String name,
        String cpf,
        LocalDate birthDate,
        String phone,
        String email,
        EnderecoDTO endereco
) {
    public PacienteDTO(Paciente paciente) {
        this(
            paciente.getId(),
            paciente.getNome(),
            paciente.getCpf(),
            paciente.getDataNascimento(),
            paciente.getTelefone(),
            paciente.getEmail(),
           (paciente.getEndereco() != null ? new EnderecoDTO(paciente.getEndereco()) : null)
        );
    }
}