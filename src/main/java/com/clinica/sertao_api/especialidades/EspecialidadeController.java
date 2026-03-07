package com.clinica.sertao_api.especialidades;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/especialidades")
public class EspecialidadeController {

    @Autowired
    private EspecialidadeService service;

    @GetMapping
    public List<Especialidade> listar() {
        return service.listarTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Especialidade> buscar(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Especialidade criar(@RequestBody Especialidade especialidade) {
        return service.salvar(especialidade);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Especialidade> atualizar(@PathVariable Long id, @RequestBody Especialidade especialidade) {
        Especialidade atualizada = service.atualizar(id, especialidade);
        if (atualizada != null) {
            return ResponseEntity.ok(atualizada);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}