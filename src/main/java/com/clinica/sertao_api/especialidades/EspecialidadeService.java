package com.clinica.sertao_api.especialidades;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EspecialidadeService {

    @Autowired
    private EspecialidadeRepository repository;

    @Transactional(readOnly = true)
    public List<EspecialidadeDTO> findAll() {
        return repository.findAll().stream()
                .map(EspecialidadeDTO::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<EspecialidadeDTO> findById(Long id) {
        return repository.findById(id).map(EspecialidadeDTO::new);
    }

    public Optional<EspecialidadeDTO> save(EspecialidadeDTO dto) {
        Especialidade especialidade = new Especialidade(dto);
        Especialidade saved = repository.save(especialidade);
        return Optional.of(new EspecialidadeDTO(saved));
    }

    public Optional<EspecialidadeDTO> update(Long id, EspecialidadeDTO dto) {
        if (repository.existsById(id)) {
            Especialidade especialidadeAtualizada = new Especialidade(dto);
            especialidadeAtualizada.setId(id);
            Especialidade saved = repository.save(especialidadeAtualizada);
            return Optional.of(new EspecialidadeDTO(saved));
        }
        return Optional.empty();
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}