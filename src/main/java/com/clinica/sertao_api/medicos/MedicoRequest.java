package com.clinica.sertao_api.medicos;

public record MedicoRequest(
    String nome,
    String crm,
    String telefone,
    String email
) {
    public MedicoDTO toMedicoDto() {
        return new MedicoDTO(
            null,
            this.nome(),
            this.crm(),
            this.telefone(),
            this.email()
        );
    }
}