package com.clinica.sertao_api.dashboard;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}