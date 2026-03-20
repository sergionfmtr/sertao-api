package com.clinica.sertao_api.relatorios;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/relatorio")
@Tag(name = "Relatórios", description = "API para relatórios e gráficos do dashboard")
public class RelatorioController {

    @Autowired
    private RelatorioService service;

    @GetMapping("/consultas-por-especialidade")
    @Operation(summary = "Consultas por especialidade", description = "Retorna a contagem de consultas por especialidade")
    public ResponseEntity<List<LabelValueDTO>> getConsultasPorEspecialidade() {
        return ResponseEntity.ok(service.getConsultasPorEspecialidade());
    }

    @GetMapping("/status-consultas")
    @Operation(summary = "Status das consultas", description = "Retorna a contagem de consultas por status")
    public ResponseEntity<List<LabelValueDTO>> getStatusConsultas() {
        return ResponseEntity.ok(service.getStatusConsultas());
    }

    @GetMapping("/evolucao-mensal")
    @Operation(summary = "Evolução mensal", description = "Retorna a contagem de consultas por dia do mês atual")
    public ResponseEntity<List<LabelValueDTO>> getEvolucaoMensal() {
        return ResponseEntity.ok(service.getEvolucaoMensal());
    }

    @GetMapping("/top-medicos")
    @Operation(summary = "Top Médicos", description = "Retorna os médicos com mais consultas realizadas")
    public ResponseEntity<List<LabelValueDTO>> getTopMedicos() {
        return ResponseEntity.ok(service.getTopMedicos());
    }

    @GetMapping("/pacientes-por-faixa-etaria")
    @Operation(summary = "Pacientes por faixa etária", description = "Retorna a contagem de pacientes agrupados por faixa etária")
    public ResponseEntity<List<LabelValueDTO>> getPacientesPorFaixaEtaria() {
        return ResponseEntity.ok(service.getPacientesPorFaixaEtaria());
    }

    @GetMapping("/consultas-por-dia-semana")
    @Operation(summary = "Consultas por dia da semana", description = "Retorna o volume de consultas por dia da semana")
    public ResponseEntity<List<LabelValueDTO>> getConsultasPorDiaSemana() {
        return ResponseEntity.ok(service.getConsultasPorDiaSemana());
    }
}