package com.clinica.sertao_api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clinica.sertao_api.medicos.Medico;
import com.clinica.sertao_api.medicos.MedicoDTO;
import com.clinica.sertao_api.medicos.MedicoRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    @Transactional(readOnly = true)
    public List<MedicoDTO> findAll() {
        List<Medico> result = medicoRepository.findAll();
        return result.stream().map(MedicoDTO::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public MedicoDTO findById(Integer id) {
        Medico entity = medicoRepository.findById(id).orElseThrow(() -> new RuntimeException("Médico não encontrado"));
        return new MedicoDTO(entity);
    }

    @Transactional
    public MedicoDTO insert(MedicoDTO dto) {
        Medico entity = new Medico(dto);
        entity = medicoRepository.save(entity);
        return new MedicoDTO(entity);
    }
    
    @Transactional
    public void delete(Integer id) {
        if (!medicoRepository.existsById(id)) {
            throw new RuntimeException("Médico não encontrado para exclusão");
        }
        medicoRepository.deleteById(id);
    }

    @Transactional
    public MedicoDTO update(Integer id, MedicoDTO dto) {
        if (!medicoRepository.existsById(id)) {
            throw new RuntimeException("Médico não encontrado para atualização");
        }
        Medico entity = new Medico(dto);
        entity.setId(id);
        entity = medicoRepository.save(entity);
        return new MedicoDTO(entity);
    }
}