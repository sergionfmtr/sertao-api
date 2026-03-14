package com.clinica.sertao_api.medicos;

import com.clinica.sertao_api.especialidades.EspecialidadeDTO;
import java.util.List;

public record MedicoResponse(
    Integer id,
    String nome,
    String crm,
    String telefone,
    String email,
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