package com.clinica.sertao_api.consultas;

import com.clinica.sertao_api.medicos.Medico;
import com.clinica.sertao_api.medicos.MedicoRepository;
import com.clinica.sertao_api.pacientes.Paciente;
import com.clinica.sertao_api.pacientes.PacienteRepository;
import com.clinica.sertao_api.infra.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository repository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Transactional(readOnly = true)
    public List<ConsultaDTO> findAll() {
        return repository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<ConsultaDTO> findById(Long id) {
        return repository.findById(id).map(this::toDto);
    }

    @Transactional
    public Optional<ConsultaDTO> save(ConsultaDTO dto) {
        Medico medico = medicoRepository.findById(dto.medicoId().intValue())
                .orElseThrow(() -> new ResourceNotFoundException("Médico não encontrado."));

        Paciente paciente = pacienteRepository.findById(dto.pacienteId())
                .orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado."));

        Consulta consulta = new Consulta();

        consulta.setMedico(medico);
        consulta.setPaciente(paciente);
        consulta.setDataConsulta(dto.dataConsulta());

        Consulta saved = repository.save(consulta);
        return Optional.of(toDto(saved));
    }

    @Transactional
    public Optional<ConsultaDTO> update(Long id, ConsultaDTO dto) {
        // Caso precise atualizar a data, médico ou paciente
        return repository.findById(id).map(consulta -> {
            consulta.setDataConsulta(dto.dataConsulta());
            return toDto(repository.save(consulta));
        });
    }

    @Transactional
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        }
    }

    private ConsultaDTO toDto(Consulta consulta) {
        Long medicoId = consulta.getMedico() != null ? consulta.getMedico().getId() : null;
        Long pacienteId = consulta.getPaciente() != null ? consulta.getPaciente().getId() : null;
        return new ConsultaDTO(consulta.getId(), medicoId, pacienteId, consulta.getDataConsulta());
    }
}