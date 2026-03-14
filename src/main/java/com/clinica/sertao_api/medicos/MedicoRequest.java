package com.clinica.sertao_api.medicos;

import java.util.List;

public record MedicoRequest(
    String nome,
    String crm,
    String telefone,
    String email,
    List<Long> especialidades
    
) {
    public MedicoDTO toMedicoDto() {
        return new MedicoDTO(
            null,
            this.nome(),
            this.crm(),
            this.telefone(),
            this.email(),
            this.especialidades()
        );
    }
}