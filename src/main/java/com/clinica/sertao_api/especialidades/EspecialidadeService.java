package com.clinica.sertao_api.especialidades;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EspecialidadeService {

    @Autowired
    private EspecialidadeRepository repository;

    public List<EspecialidadeDTO> findAll() {
        return repository.findAll().stream()
                .map(EspecialidadeDTO::new)
                .collect(Collectors.toList());
    }

    public Optional<Especialidade> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public Especialidade salvar(Especialidade especialidade) {
        return repository.save(especialidade);
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