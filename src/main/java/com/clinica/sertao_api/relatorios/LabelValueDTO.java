package com.clinica.sertao_api.relatorios;

import io.swagger.v3.oas.annotations.media.Schema;

public record LabelValueDTO(
        @Schema(description = "Rótulo do dado (Ex: nome da especialidade, faixa etária)", example = "Cardiologia")
        String label,

        @Schema(description = "Valor numérico correspondente (Ex: quantidade de consultas)", example = "15")
        Long value
) {}