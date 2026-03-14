package com.clinica.sertao_api.especialidades;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/especialidades")
@Tag(name = "Especialidades", description = "API para gerenciamento de especialidades médicas")
public class EspecialidadeController {

    @Autowired
    private EspecialidadeService service;

    @GetMapping
    @Operation(summary = "Listar todas as especialidades", description = "Retorna uma lista com todas as especialidades cadastradas")
    public ResponseEntity<List<EspecialidadeResponse>> findAll() {
        List<EspecialidadeResponse> especialidades = service.findAll().stream()
                .map(EspecialidadeResponse::toResponse) 
                .collect(Collectors.toList());
        return ResponseEntity.ok(especialidades);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar especialidade por ID", description = "Retorna uma especialidade específica baseada no ID fornecido")
    public ResponseEntity<EspecialidadeResponse> findById(@PathVariable Long id) {
        return service.findById(id)
                .map(EspecialidadeResponse::toResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Criar nova especialidade", description = "Cria uma nova especialidade com os dados fornecidos")
    public ResponseEntity<EspecialidadeResponse> save(@RequestBody EspecialidadeRequest request) {
        return service.save(request.toEspecialidadeDto())
                .map(EspecialidadeResponse::toResponse)
                .map(response -> ResponseEntity.status(HttpStatus.CREATED).body(response))
                .orElse(ResponseEntity.badRequest().build());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar especialidade", description = "Atualiza os dados de uma especialidade existente")
    public ResponseEntity<EspecialidadeResponse> atualizar(@PathVariable Long id, @RequestBody EspecialidadeRequest request) {
        return service.update(id, request.toEspecialidadeDto())
                .map(EspecialidadeResponse::toResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar especialidade", description = "Remove uma especialidade do sistema")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}