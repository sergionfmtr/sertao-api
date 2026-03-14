package com.clinica.sertao_api.pacientes;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public record PacienteRequest(
    @NotBlank @Size(max = 150) String name,
    @NotBlank @Size(max = 14) String cpf,
    @NotNull LocalDate birthDate,
    @Size(max = 18) String phone,
    @Email @Size(max = 100) String email
) {
    public PacienteDTO toPacienteDto() {
        return new PacienteDTO(null, name, cpf, birthDate, phone, email);
    }
}