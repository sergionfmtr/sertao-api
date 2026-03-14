package com.clinica.sertao_api.medicos;

import com.clinica.sertao_api.especialidades.EspecialidadeDTO;

import java.util.List;

public record MedicoDTO(
    Integer id,
    String nome,
    String crm,
    String telefone,
    String email,
    List<EspecialidadeDTO> especialidades
) {
    public MedicoDTO(Medico medico) {
        this(
            medico.getId(),
            medico.getNome(),
            medico.getCrm(),
            medico.getTelefone(),
            medico.getEmail(),
            (medico.getEspecialidades() != null ? medico.getEspecialidades().stream().map(EspecialidadeDTO::new).toList() : null)
        );
    }
}