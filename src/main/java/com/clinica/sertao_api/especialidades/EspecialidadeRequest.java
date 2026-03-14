package com.clinica.sertao_api.especialidades;

import jakarta.validation.constraints.NotBlank;

public record EspecialidadeRequest(
        @NotBlank(message = "O nome da especialidade é obrigatório.")
        String nome
) {

    public EspecialidadeDTO toEspecialidadeDto() {
        return new EspecialidadeDTO(null, nome());
    }
}