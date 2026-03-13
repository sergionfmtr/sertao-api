package com.clinica.sertao_api.medicos;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.clinica.sertao_api.MedicoService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/medicos")
@Tag(name = "Médicos", description = "Controller para gerenciamento de médicos")
public class MedicoController {

    private final MedicoService medicoService;

    public MedicoController(MedicoService medicoService) {
        this.medicoService = medicoService;
    }

    @GetMapping
    @Operation(summary = "Busca todos os médicos", description = "Retorna uma lista completa de médicos cadastrados")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    })
    public ResponseEntity<List<MedicoDTO>> findAll() {
        List<MedicoDTO> list = medicoService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Busca médico por ID", description = "Retorna um médico específico dado o seu ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Médico encontrado"),
        @ApiResponse(responseCode = "404", description = "Médico não encontrado")
    })
    public ResponseEntity<MedicoDTO> findById(@PathVariable Integer id) {
        MedicoDTO dto = medicoService.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    @Operation(summary = "Insere um novo médico", description = "Cadastra um novo médico e retorna os dados inseridos")
    @ApiResponse(responseCode = "201", description = "Médico criado com sucesso")
    public ResponseEntity<MedicoDTO> insert(@RequestBody MedicoDTO dto) {
        dto = medicoService.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Remove um médico", description = "Remove um médico do sistema pelo ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Médico removido com sucesso"),
        @ApiResponse(responseCode = "404", description = "Médico não encontrado")
    })
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        medicoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    @Operation(summary = "Atualiza um médico", description = "Atualiza os dados de um médico existente")
    @ApiResponse(responseCode = "200", description = "Médico atualizado com sucesso")
    public ResponseEntity<MedicoDTO> update(@PathVariable Integer id, @RequestBody MedicoDTO dto) {
        dto = medicoService.update(id, dto);
        return ResponseEntity.ok().body(dto);
    }
}