package com.clinica.sertao_api.consultas;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/consultas")
@Tag(name = "Consultas", description = "API para agendamento e gerenciamento de consultas médicas")
public class ConsultaController {

    @Autowired
    private ConsultaService service;

    @GetMapping
    @Operation(summary = "Listar todas as consultas", description = "Retorna uma lista de todas as consultas registradas, permitindo filtros opcionais")
    public ResponseEntity<List<ConsultaResponse>> findAll(
            @Parameter(description = "Filtrar pelo ID do médico") @RequestParam(required = false) Long medicoId,
            @Parameter(description = "Filtrar pelo ID do paciente") @RequestParam(required = false) Long pacienteId,
            @Parameter(description = "Filtrar pelo ID da especialidade") @RequestParam(required = false) Long especialidadeId,
            @Parameter(description = "Data inicial para o filtro de período", example = "2026-10-15T00:00:00") @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
            @Parameter(description = "Data final para o filtro de período", example = "2026-10-15T23:59:59") @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal,
            @Parameter(description = "Filtrar pelo status da consulta", example = "AGENDADA") @RequestParam(required = false) ConsultaStatus status) {
        
        List<ConsultaResponse> responses = service.findAll(medicoId, pacienteId, especialidadeId, dataInicial, dataFinal, status).stream()
                .map(ConsultaResponse::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar consulta por ID", description = "Retorna uma consulta específica com base no ID fornecido")
    public ResponseEntity<ConsultaResponse> findById(@PathVariable Long id) {
        return service.findById(id)
                .map(ConsultaResponse::toResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Agendar nova consulta", description = "Cria um novo agendamento de consulta com os dados fornecidos")
    public ResponseEntity<ConsultaResponse> save(@RequestBody @Valid ConsultaRequest request) {
        return service.save(request.toConsultaDto())
                .map(ConsultaResponse::toResponse)
                .map(response -> ResponseEntity.status(HttpStatus.CREATED).body(response))
                .orElse(ResponseEntity.badRequest().build());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar agendamento", description = "Atualiza os dados de uma consulta existente")
    public ResponseEntity<ConsultaResponse> update(@PathVariable Long id, @RequestBody @Valid ConsultaRequest request) {
        return service.update(id, request.toConsultaDto())
                .map(ConsultaResponse::toResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Cancelar consulta", description = "Cancela uma consulta através da exclusão do registro")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/status")
    @Operation(summary = "Atualizar status da consulta", description = "Altera o status de uma consulta (ex: AGENDADA -> REALIZADA)")
    public ResponseEntity<ConsultaResponse> updateStatus(@PathVariable Long id, @RequestBody java.util.Map<String, String> body) {
        ConsultaStatus status = ConsultaStatus.valueOf(body.get("status"));
        return service.updateStatus(id, status)
                .map(ConsultaResponse::toResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}