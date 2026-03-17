package com.clinica.sertao_api.dashboard;

import com.clinica.sertao_api.consultas.ConsultaResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/dashboard")
@Tag(name = "Dashboard", description = "API para visualização de métricas e painéis da clínica")
public class DashboardController {

    @Autowired
    private DashboardService service;

    @GetMapping
    @Operation(summary = "Obter dados do dashboard", description = "Retorna as métricas do mês atual, incluindo a quantidade de consultas")
    public ResponseEntity<DashboardResponse> getDashboard() {
        return ResponseEntity.ok(service.getResumoDoMes());
    }

    @GetMapping("/ultimos-agendamentos")
    @Operation(summary = "Últimos agendamentos", description = "Retorna uma lista com os 10 últimos agendamentos registrados")
    public ResponseEntity<List<ConsultaResponse>> getUltimosAgendamentos() {
        List<ConsultaResponse> ultimos = service.getUltimosAgendamentos().stream()
                .map(ConsultaResponse::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(ultimos);
    }
}