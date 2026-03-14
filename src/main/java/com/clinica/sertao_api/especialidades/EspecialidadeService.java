package com.clinica.sertao_api.especialidades;

import com.clinica.sertao_api.infra.exception.BusinessException;
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

    @Transactional
    public Optional<EspecialidadeDTO> save(EspecialidadeDTO dto) {
        repository.findByNome(dto.nome()).ifPresent(e -> {
            throw new BusinessException("Já existe uma especialidade com o nome " + dto.nome());
        });

        Especialidade especialidade = new Especialidade(dto);
        Especialidade saved = repository.save(especialidade);
        return Optional.of(new EspecialidadeDTO(saved));
    }

    @Transactional
    public Optional<EspecialidadeDTO> update(Long id, EspecialidadeDTO dto) {
        repository.findByNome(dto.nome()).ifPresent(e -> {
            if (!e.getId().equals(id)) {
                throw new BusinessException("Já existe uma especialidade com o nome " + dto.nome());
            }
        });

        if (repository.existsById(id)) {
            Especialidade especialidadeAtualizada = new Especialidade(dto);
            especialidadeAtualizada.setId(id);
            Especialidade saved = repository.save(especialidadeAtualizada);
            return Optional.of(new EspecialidadeDTO(saved));
        }
        return Optional.empty();
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}