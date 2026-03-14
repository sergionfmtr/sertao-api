package com.clinica.sertao_api.pacientes;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public record PacienteRequest(
    
    @NotBlank(message = "O nome é obrigatório.") 
    @Size(max = 150, message = "O nome não pode exceder 150 caracteres.") 
    String name,

    @NotBlank(message = "O CPF é obrigatório.") 
    @Size(max = 14, message = "O CPF deve ter no máximo 14 caracteres.") 
    String cpf,

    @NotNull(message = "A data de nascimento é obrigatória.") 
    LocalDate birthDate,

    @Size(max = 18, message = "O telefone não pode exceder 18 caracteres.") 
    String phone,

    @Email(message = "O e-mail deve ser um endereço válido.") 
    @Size(max = 100, message = "O e-mail não pode exceder 100 caracteres.") 
    String email
) {
    public PacienteDTO toPacienteDto() {
        return new PacienteDTO(null, name, cpf, birthDate, phone, email);
    }
}