package com.clinica.sertao_api.medicos;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/medicos")
@Tag(name = "Médicos", description = "API para gerenciamento de médicos")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @GetMapping
    @Operation(summary = "Listar todos os médicos", description = "Retorna uma lista com todos os médicos cadastrados e suas especialidades")
    public ResponseEntity<List<MedicoResponse>> findAll() {
        List<MedicoResponse> list = medicoService.findAll()
                                                .stream()
                                                .map(MedicoResponse::toResponse)
                                                .toList();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Buscar médico por ID", description = "Retorna um médico específico baseado no ID fornecido")
    public ResponseEntity<MedicoResponse> findById(@Parameter(description = "ID do médico a ser buscado", required = true) @PathVariable Integer id) {
        MedicoDTO dto = medicoService.findById(id);
        return ResponseEntity.ok().body(MedicoResponse.toResponse(dto));
    }

    @PostMapping
    @Operation(summary = "Cadastrar novo médico", description = "Cria um novo médico com os dados fornecidos")
    public ResponseEntity<MedicoResponse> insert(@RequestBody @Valid MedicoRequest request) {
        MedicoDTO dto = request.toMedicoDto();
        dto = medicoService.save(dto);
        
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.id()).toUri();
        return ResponseEntity.created(uri).body(MedicoResponse.toResponse(dto));
    }

    @PutMapping(value = "/{id}")
    @Operation(summary = "Atualizar médico", description = "Atualiza os dados de um médico existente")
    public ResponseEntity<MedicoResponse> update(@Parameter(description = "ID do médico a ser atualizado", required = true) @PathVariable Integer id, @RequestBody @Valid MedicoRequest request) {
        MedicoDTO dto = request.toMedicoDto();
        dto = medicoService.update(id, dto);
        return ResponseEntity.ok().body(MedicoResponse.toResponse(dto));
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Deletar médico", description = "Remove um médico do sistema")
    public ResponseEntity<Void> delete(@Parameter(description = "ID do médico a ser deletado", required = true) @PathVariable Integer id) {
        medicoService.delete(id);
        return ResponseEntity.noContent().build();
    }

 
}