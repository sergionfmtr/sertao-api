package com.clinica.sertao_api.medicos;

import com.clinica.sertao_api.especialidades.EspecialidadeDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record MedicoRequest(
    @Schema(description = "Nome completo do médico", example = "Dr. João Silva")
    @NotBlank(message = "O nome é obrigatório.")
    String nome,

    @Schema(description = "Registro de CRM do médico", example = "123456-SP")
    @NotBlank(message = "O CRM é obrigatório.")
    String crm,

    @Schema(description = "Telefone de contato do médico", example = "(11) 98765-4321")
    @NotBlank(message = "O telefone é obrigatório.")
    String telefone,

    @Schema(description = "Endereço de e-mail do médico", example = "joao.silva@clinica.com.br")
    @NotBlank(message = "O email é obrigatório.")
    @Email(message = "O email deve ser válido.")
    String email,

    @Schema(description = "Lista de IDs das especialidades que o médico atende", example = "[1, 2]")
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