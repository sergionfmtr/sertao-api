package com.clinica.sertao_api.medicos;

import com.clinica.sertao_api.especialidades.Especialidade;
import com.clinica.sertao_api.especialidades.EspecialidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private EspecialidadeRepository especialidadeRepository;

    @Transactional(readOnly = true)
    public List<MedicoDTO> findAll() {
        List<Medico> result = medicoRepository.findAllWithEspecialidades();
        return result.stream().map(MedicoDTO::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public MedicoDTO findById(Integer id) {
        Medico entity = medicoRepository.findById(id).orElseThrow(() -> new RuntimeException("Médico não encontrado"));
        return new MedicoDTO(entity);
    }
    
    @Transactional
    public MedicoDTO save(MedicoDTO dto) {
        Medico entity = new Medico();
        copyDtoToEntity(dto, entity);
        entity = medicoRepository.save(entity);
        return new MedicoDTO(entity);
    }

    @Transactional
    public MedicoDTO update(Integer id, MedicoDTO dto) {
        Medico entity = medicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Médico não encontrado para atualização"));
        copyDtoToEntity(dto, entity);
        entity = medicoRepository.save(entity);
        return new MedicoDTO(entity);
    }

    private void copyDtoToEntity(MedicoDTO dto, Medico entity) {
        entity.setNome(dto.nome());
        entity.setCrm(dto.crm());
        entity.setEmail(dto.email());
        entity.setTelefone(dto.telefone());

        entity.getEspecialidades().clear();
        if (dto.especialidades() != null && !dto.especialidades().isEmpty()) {
            List<Long> especialidadeIds = dto.especialidades().stream().map(e -> e.id()).collect(Collectors.toList());
            List<Especialidade> especialidades = especialidadeRepository.findAllById(especialidadeIds);
            if (especialidades.size() != dto.especialidades().size()) {
                throw new RuntimeException("Uma ou mais especialidades não foram encontradas.");
            }
            entity.getEspecialidades().addAll(especialidades);
        }
    }

    @Transactional
    public void delete(Integer id) {
        if (!medicoRepository.existsById(id)) {
            throw new RuntimeException("Médico não encontrado para exclusão");
        }
        medicoRepository.deleteById(id);
    }
}