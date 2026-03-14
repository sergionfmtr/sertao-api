package com.clinica.sertao_api.pacientes;

import com.clinica.sertao_api.infra.exception.BusinessException;
import com.clinica.sertao_api.pacientes.enderecos.Endereco;
import com.clinica.sertao_api.pacientes.enderecos.EnderecoDTO;

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
        validateCpfUniqueness(dto.cpf(), null);

        Paciente paciente = mapToEntity(dto);
        Paciente saved = repository.save(paciente);
        return Optional.of(new PacienteDTO(saved));
    }

    @Transactional
    public Optional<PacienteDTO> update(Long id, PacienteDTO dto) {
        return repository.findById(id).map(pacienteExistente -> {
            validateCpfUniqueness(dto.cpf(), id);
            
            // Atualiza dados básicos
            updateBasicInfo(pacienteExistente, dto);
            
            // Atualiza ou cria o endereço vinculado
            updateAddressInfo(pacienteExistente, dto.endereco());

            return new PacienteDTO(repository.save(pacienteExistente));
        });
    }

    private void validateCpfUniqueness(String cpf, Long id) {
        repository.findByCpf(cpf).ifPresent(p -> {
            if (id == null || !p.getId().equals(id)) {
                throw new BusinessException("O CPF " + cpf + " já está em uso.");
            }
        });
    }

    private void updateBasicInfo(Paciente paciente, PacienteDTO dto) {
        paciente.setNome(dto.name());
        paciente.setCpf(dto.cpf());
        paciente.setDataNascimento(dto.birthDate());
        paciente.setTelefone(dto.phone());
        paciente.setEmail(dto.email());
    }

    private void updateAddressInfo(Paciente paciente, EnderecoDTO addressDto) {
        if (addressDto == null) return;

        Endereco endereco = (paciente.getEndereco() != null) 
                            ? paciente.getEndereco() 
                            : new Endereco();
        
        endereco.setCep(addressDto.cep());
        endereco.setLogradouro(addressDto.logradouro());
        endereco.setNumero(addressDto.numero());
        endereco.setComplemento(addressDto.complemento());
        endereco.setBairro(addressDto.bairro());
        endereco.setCidade(addressDto.cidade());
        endereco.setEstado(addressDto.estado());
        
        paciente.setEndereco(endereco);
    }

    private Paciente mapToEntity(PacienteDTO dto) {
        Paciente paciente = Paciente.builder()
                .nome(dto.name())
                .cpf(dto.cpf())
                .dataNascimento(dto.birthDate())
                .telefone(dto.phone())
                .email(dto.email())
                .build();

        if (dto.endereco() != null) {
            Endereco endereco = Endereco.builder()
                        .cep(dto.endereco().cep())
                        .logradouro(dto.endereco().logradouro())
                        .numero(dto.endereco().numero())
                        .complemento(dto.endereco().complemento())
                        .bairro(dto.endereco().bairro())
                        .cidade(dto.endereco().cidade())
                        .estado(dto.endereco().estado())
                        .build();
                paciente.setEndereco(endereco);
        }
        return paciente;
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }


    /*
    private Paciente mapToEntity(PacienteDTO dto) {
        return Paciente.builder()
        .nome(dto.name())
        .cpf(dto.cpf())
        .dataNascimento(dto.birthDate())
        .telefone(dto.phone())
        .email(dto.email())
        .build();
    }
    */
}