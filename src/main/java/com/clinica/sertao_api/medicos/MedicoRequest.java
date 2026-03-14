package com.clinica.sertao_api.medicos;

import com.clinica.sertao_api.especialidades.EspecialidadeDTO;

import java.util.List;

public record MedicoRequest(
    String nome,
    String crm,
    String telefone,
    String email,
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