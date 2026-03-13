package com.clinica.sertao_api.medicos;

public record MedicoRequest(
    String nome,
    String crm,
    String telefone,
    String email
) {
}