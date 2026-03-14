package com.clinica.sertao_api.pacientes;

import com.clinica.sertao_api.infra.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository repository;

    @Transactional(readOnly = true)
    public List<PacienteDTO> findAll() {
        return repository.findAll().stream()
                .map(PacienteDTO::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<PacienteDTO> findById(Long id) {
        return repository.findById(id).map(PacienteDTO::new);
    }

    @Transactional
    public Optional<PacienteDTO> save(PacienteDTO dto) {
        repository.findByCpf(dto.cpf()).ifPresent(p -> {
            throw new BusinessException("Já existe um paciente com o CPF " + dto.cpf());
        });

        Paciente paciente = mapToEntity(dto);
        Paciente saved = repository.save(paciente);
        return Optional.of(new PacienteDTO(saved));
    }

    @Transactional
    public Optional<PacienteDTO> update(Long id, PacienteDTO dto) {
        repository.findByCpf(dto.cpf()).ifPresent(p -> {
            if (!p.getId().equals(id)) {
                throw new BusinessException("O CPF " + dto.cpf() + " já está em uso por outro paciente.");
            }
        });

        if (repository.existsById(id)) {
            Paciente paciente = mapToEntity(dto);
            paciente.setId(id);
            Paciente saved = repository.save(paciente);
            return Optional.of(new PacienteDTO(saved));
        }
        return Optional.empty();
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

    private Paciente mapToEntity(PacienteDTO dto) {
        return Paciente.builder()
                .nome(dto.name())
                .cpf(dto.cpf())
                .dataNascimento(dto.birthDate())
                .telefone(dto.phone())
                .email(dto.email())
                .build();
    }
}