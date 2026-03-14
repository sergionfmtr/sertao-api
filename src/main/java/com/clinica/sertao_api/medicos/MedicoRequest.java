package com.clinica.sertao_api.medicos;

import com.clinica.sertao_api.especialidades.EspecialidadeDTO;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record MedicoRequest(
    @NotBlank(message = "O nome é obrigatório.")
    String nome,
    @NotBlank(message = "O CRM é obrigatório.")
    String crm,
    @NotBlank(message = "O telefone é obrigatório.")
    String telefone,
    @NotBlank(message = "O email é obrigatório.")
    @Email(message = "O email deve ser válido.")
    String email,
    @NotEmpty(message = "A lista de especialidades é obrigatória.")
    List<Long> especialidades

) {
    public MedicoDTO toMedicoDto() {
        List<EspecialidadeDTO> especialidadeDTOs = null;
        if (this.especialidades() != null) {
            especialidadeDTOs = this.especialidades().stream()
                .map(id -> new EspecialidadeDTO(id, null))
                .toList();
        }

        return new MedicoDTO(
            null,
            this.nome(),
            this.crm(),
            this.telefone(),
            this.email(),
            especialidadeDTOs
        );
    }
}