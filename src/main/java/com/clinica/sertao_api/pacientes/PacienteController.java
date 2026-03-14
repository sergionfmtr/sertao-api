package com.clinica.sertao_api.pacientes;

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
@RequestMapping("/pacientes")
@Tag(name = "Pacientes", description = "API para gerenciamento de pacientes da clínica")
public class PacienteController {

    @Autowired
    private PacienteService service;

    @GetMapping
    @Operation(summary = "Listar todos os pacientes", description = "Retorna uma lista de todos os pacientes registrados")
    public ResponseEntity<List<PacienteResponse>> findAll() {
        List<PacienteResponse> responses = service.findAll().stream()
                .map(PacienteResponse::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar paciente por ID", description = "Retorna um paciente específico com base no ID fornecido")
    public ResponseEntity<PacienteResponse> findById(@PathVariable Long id) {
        return service.findById(id)
                .map(PacienteResponse::toResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Criar novo paciente", description = "Cria um novo paciente com os dados fornecidos")
    public ResponseEntity<PacienteResponse> save(@RequestBody @Valid PacienteRequest request) {
        return service.save(request.toPacienteDto())
                .map(PacienteResponse::toResponse)
                .map(response -> ResponseEntity.status(HttpStatus.CREATED).body(response))
                .orElse(ResponseEntity.badRequest().build());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar paciente", description = "Atualiza os dados de um paciente existente")
    public ResponseEntity<PacienteResponse> update(@PathVariable Long id, @RequestBody @Valid PacienteRequest request) {
        return service.update(id, request.toPacienteDto())
                .map(PacienteResponse::toResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir paciente", description = "Excluir paciente")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}