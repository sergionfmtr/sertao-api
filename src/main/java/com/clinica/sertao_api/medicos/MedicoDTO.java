package com.clinica.sertao_api.medicos;

public record MedicoDTO(
    Integer id,
    String nome,
    String crm,
    String telefone,
    String email
) {
    public MedicoDTO(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getCrm(), medico.getTelefone(), medico.getEmail());
    }
}