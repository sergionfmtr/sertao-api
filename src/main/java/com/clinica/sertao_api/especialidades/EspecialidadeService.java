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

    public Especialidade atualizar(Long id, Especialidade especialidadeAtualizada) {
        if (repository.existsById(id)) {
            especialidadeAtualizada.setId(id);
            return repository.save(especialidadeAtualizada);
        }
        return null;
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}