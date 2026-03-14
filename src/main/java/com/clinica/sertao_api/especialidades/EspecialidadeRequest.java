package com.clinica.sertao_api.especialidades;

public record EspecialidadeRequest(
        String nome
) {

    public EspecialidadeDTO toEspecialidadeDto() {
        return new EspecialidadeDTO(null, nome());
    }
}