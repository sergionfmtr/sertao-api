package com.clinica.sertao_api.especialidades;

public record EspecialidadeDTO(
        Long id,
        String nome
) {
    public EspecialidadeDTO(Especialidade especialidade) {
        this(especialidade.getId(), especialidade.getNome());
    }
}