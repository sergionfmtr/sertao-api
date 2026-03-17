package com.clinica.sertao_api.dashboard;

import io.swagger.v3.oas.annotations.media.Schema;

public record DashboardResponse(
    @Schema(description = "Quantidade total de consultas no mês atual", example = "45")
    Long consultasNoMes,

    @Schema(description = "Quantidade de pacientes únicos atendidos no mês atual", example = "30")
    Long pacientesAtendidosNoMes,

    @Schema(description = "Quantidade de consultas pendentes a realizar no mês atual", example = "15")
    Long consultasPendentesNoMes
) {
}