package com.clinica.sertao_api.medicos;

import com.clinica.sertao_api.especialidades.EspecialidadeDTO;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public record MedicoResponse(
    @Schema(description = "ID único do médico", example = "1")
    Long id,

    @Schema(description = "Nome completo do médico", example = "Dr. João Silva")
    String nome,

    @Schema(description = "Registro de CRM do médico", example = "123456-SP")
    String crm,

    @Schema(description = "Telefone de contato do médico", example = "(11) 98765-4321")
    String telefone,

    @Schema(description = "Endereço de e-mail do médico", example = "joao.silva@clinica.com.br")
    String email,

    @Schema(description = "Lista de especialidades vinculadas ao médico")
    List<EspecialidadeDTO> especialidades
) {
    public MedicoResponse(Medico medico) {
        this(
            medico.getId(),
            medico.getNome(),
            medico.getCrm(),
            medico.getTelefone(),
            medico.getEmail(),
            medico.getEspecialidades().stream().map(EspecialidadeDTO::new).toList()
        );
    }

    public static MedicoResponse toResponse(MedicoDTO dto) {
        if (dto == null) {
            return null;
        }
        return new MedicoResponse(
            dto.id(),
            dto.nome(),
            dto.crm(),
            dto.telefone(),
            dto.email(),
            dto.especialidades()
        );
    }
}