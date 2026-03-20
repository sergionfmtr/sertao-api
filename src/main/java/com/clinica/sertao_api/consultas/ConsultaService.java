package com.clinica.sertao_api.consultas;

import com.clinica.sertao_api.especialidades.Especialidade;
import com.clinica.sertao_api.especialidades.EspecialidadeDTO;
import com.clinica.sertao_api.especialidades.EspecialidadeRepository;
import com.clinica.sertao_api.medicos.Medico;
import com.clinica.sertao_api.medicos.MedicoDTO;
import com.clinica.sertao_api.medicos.MedicoRepository;
import com.clinica.sertao_api.pacientes.Paciente;
import com.clinica.sertao_api.pacientes.PacienteDTO;
import com.clinica.sertao_api.pacientes.PacienteRepository;
import com.clinica.sertao_api.infra.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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

    @Autowired
    private EspecialidadeRepository especialidadeRepository;

    @Transactional(readOnly = true)
    public List<ConsultaDTO> findAll(Long medicoId, Long pacienteId, Long especialidadeId, LocalDateTime dataInicial, LocalDateTime dataFinal) {
        Specification<Consulta> spec = Specification.where(null);

        if (medicoId != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("medico").get("id"), medicoId));
        }
        if (pacienteId != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("paciente").get("id"), pacienteId));
        }
        if (especialidadeId != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("especialidade").get("id"), especialidadeId));
        }
        if (dataInicial != null) {
            spec = spec.and((root, query, cb) -> cb.greaterThanOrEqualTo(root.get("dataConsulta"), dataInicial));
        }
        if (dataFinal != null) {
            spec = spec.and((root, query, cb) -> cb.lessThanOrEqualTo(root.get("dataConsulta"), dataFinal));
        }

        return repository.findAll(spec).stream()
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

        Especialidade especialidade = especialidadeRepository.findById(dto.especialidadeId())
                .orElseThrow(() -> new ResourceNotFoundException("Especialidade não encontrada."));

        Consulta consulta = new Consulta();

        consulta.setMedico(medico);
        consulta.setPaciente(paciente);
        consulta.setEspecialidade(especialidade);
        consulta.setDataConsulta(dto.dataConsulta());
        consulta.setStatus(dto.status() != null ? dto.status() : ConsultaStatus.AGENDADA);

        Consulta saved = repository.save(consulta);
        return Optional.of(toDto(saved));
    }

    @Transactional
    public Optional<ConsultaDTO> update(Long id, ConsultaDTO dto) {
        // Caso precise atualizar a data, médico ou paciente
        return repository.findById(id).map(consulta -> {
            consulta.setDataConsulta(dto.dataConsulta());

            if (dto.status() != null) {
                consulta.setStatus(dto.status());
            }

            // Atualiza o médico caso o ID enviado seja diferente do atual
            if (consulta.getMedico() == null || consulta.getMedico().getId().longValue() != dto.medicoId().longValue()) {
                Medico medico = medicoRepository.findById(dto.medicoId().intValue())
                        .orElseThrow(() -> new ResourceNotFoundException("Médico não encontrado."));
                consulta.setMedico(medico);
            }

            // Atualiza o paciente caso o ID enviado seja diferente do atual
            if (consulta.getPaciente() == null || consulta.getPaciente().getId().longValue() != dto.pacienteId().longValue()) {
                Paciente paciente = pacienteRepository.findById(dto.pacienteId())
                        .orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado."));
                consulta.setPaciente(paciente);
            }

            // Atualiza a especialidade caso o ID enviado seja diferente do atual
            if (consulta.getEspecialidade() == null || consulta.getEspecialidade().getId().longValue() != dto.especialidadeId().longValue()) {
                Especialidade especialidade = especialidadeRepository.findById(dto.especialidadeId())
                        .orElseThrow(() -> new ResourceNotFoundException("Especialidade não encontrada."));
                consulta.setEspecialidade(especialidade);
            }

            return toDto(repository.save(consulta));
        });
    }

    @Transactional
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        }
    }

    @Transactional
    public Optional<ConsultaDTO> updateStatus(Long id, ConsultaStatus status) {
        return repository.findById(id).map(consulta -> {
            consulta.setStatus(status);
            return toDto(repository.save(consulta));
        });
    }

    private ConsultaDTO toDto(Consulta consulta) {
        Long medicoId = consulta.getMedico() != null ? consulta.getMedico().getId() : null;
        Long pacienteId = consulta.getPaciente() != null ? consulta.getPaciente().getId() : null;
        Long especialidadeId = consulta.getEspecialidade() != null ? consulta.getEspecialidade().getId() : null;

        MedicoDTO medico = consulta.getMedico() != null ? new MedicoDTO(consulta.getMedico()) : null;
        PacienteDTO paciente = consulta.getPaciente() != null ? new PacienteDTO(consulta.getPaciente()) : null;
        EspecialidadeDTO especialidade = consulta.getEspecialidade() != null ? new EspecialidadeDTO(consulta.getEspecialidade()) : null;

        return new ConsultaDTO(consulta.getId(), medicoId, pacienteId, especialidadeId, medico, paciente, especialidade, consulta.getDataConsulta(), consulta.getStatus());
    }
}