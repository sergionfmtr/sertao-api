package com.clinica.sertao_api.medicos;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/medicos")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @GetMapping
    public ResponseEntity<List<MedicoResponse>> findAll() {
        List<MedicoResponse> list = medicoService.findAll()
                                                .stream()
                                                .map(MedicoResponse::toResponse)
                                                .toList();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<MedicoResponse> findById(@PathVariable Integer id) {
        MedicoDTO dto = medicoService.findById(id);
        return ResponseEntity.ok().body(MedicoResponse.toResponse(dto));
    }

    @PostMapping
    public ResponseEntity<MedicoResponse> insert(@RequestBody @Valid MedicoRequest request) {
        MedicoDTO dto = request.toMedicoDto();
        dto = medicoService.save(dto);
        
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.id()).toUri();
        return ResponseEntity.created(uri).body(MedicoResponse.toResponse(dto));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<MedicoResponse> update(@PathVariable Integer id, @RequestBody @Valid MedicoRequest request) {
        MedicoDTO dto = request.toMedicoDto();
        dto = medicoService.update(id, dto);
        return ResponseEntity.ok().body(MedicoResponse.toResponse(dto));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        medicoService.delete(id);
        return ResponseEntity.noContent().build();
    }

 
}