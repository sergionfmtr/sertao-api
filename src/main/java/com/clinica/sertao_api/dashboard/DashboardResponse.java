package com.clinica.sertao_api.dashboard;

import io.swagger.v3.oas.annotations.media.Schema;

public record DashboardResponse(
    @Schema(description = "Quantidade total de consultas no mês atual", example = "45")
    Long consultasNoMes
) {
}