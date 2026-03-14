package com.clinica.sertao_api.medicos;

import java.util.List;

public record MedicoDTO(
    Integer id,
    String nome,
    String crm,
    String telefone,
    String email,
    List<Long> especialidades
) {
    public MedicoDTO(Medico medico) {
        this(
            medico.getId(),
            medico.getNome(),
            medico.getCrm(),
            medico.getTelefone(),
            medico.getEmail(),
            (medico.getEspecialidades() != null ? medico.getEspecialidades().stream().map(e -> e.getId()).toList() : null)
        );
    }
}