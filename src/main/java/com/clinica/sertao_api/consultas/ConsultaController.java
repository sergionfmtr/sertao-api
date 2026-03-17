package com.clinica.sertao_api.consultas;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/consultas")
@Tag(name = "Consultas", description = "API para agendamento e gerenciamento de consultas médicas")
public class ConsultaController {

    @Autowired
    private ConsultaService service;

    @GetMapping
    @Operation(summary = "Listar todas as consultas", description = "Retorna uma lista de todas as consultas registradas")
    public ResponseEntity<List<ConsultaResponse>> findAll() {
        List<ConsultaResponse> responses = service.findAll().stream()
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
}