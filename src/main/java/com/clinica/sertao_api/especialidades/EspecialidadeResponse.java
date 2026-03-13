package com.clinica.sertao_api.especialidades;

public record EspecialidadeResponse(
        Long id,
        String nome
) {
    public EspecialidadeResponse(Especialidade especialidade) {
        this(especialidade.getId(), especialidade.getNome());
    }

    public static EspecialidadeResponse toResponse(EspecialidadeDTO dto) {
        return new EspecialidadeResponse(dto.id(), dto.nome());
    }
}