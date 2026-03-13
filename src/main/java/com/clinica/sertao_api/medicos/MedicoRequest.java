package com.clinica.sertao_api.medicos;

public record MedicoRequest(
    String nome,
    String crm,
    String telefone,
    String email
) {
    public MedicoDTO toMedicoDto() {
        MedicoDTO dto = new MedicoDTO();
        dto.setNome(this.nome());
        dto.setCrm(this.crm());
        dto.setTelefone(this.telefone());
        dto.setEmail(this.email());
        return dto;
    }
}