package com.clinica.sertao_api.especialidades;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/especialidades")
@Tag(name = "Especialidades", description = "API para gerenciamento de especialidades médicas")
public class EspecialidadeController {

    @Autowired
    private EspecialidadeService service;

    @GetMapping
    @Operation(summary = "Listar todas as especialidades", description = "Retorna uma lista com todas as especialidades cadastradas")
    public List<Especialidade> listar() {
        return service.listarTodas();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar especialidade por ID", description = "Retorna uma especialidade específica baseada no ID fornecido")
    public ResponseEntity<Especialidade> buscar(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Criar nova especialidade", description = "Cria uma nova especialidade com os dados fornecidos")
    public Especialidade criar(@RequestBody Especialidade especialidade) {
        return service.salvar(especialidade);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar especialidade", description = "Atualiza os dados de uma especialidade existente")
    public ResponseEntity<Especialidade> atualizar(@PathVariable Long id, @RequestBody Especialidade especialidade) {
        Especialidade atualizada = service.atualizar(id, especialidade);
        if (atualizada != null) {
            return ResponseEntity.ok(atualizada);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar especialidade", description = "Remove uma especialidade do sistema")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}