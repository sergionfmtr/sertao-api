package com.clinica.sertao_api.consultas;

import com.clinica.sertao_api.especialidades.EspecialidadeDTO;
import com.clinica.sertao_api.medicos.MedicoDTO;
import com.clinica.sertao_api.pacientes.PacienteDTO;

import java.time.LocalDateTime;

public record ConsultaDTO(
    Long id,
    Long medicoId,
    Long pacienteId,
    Long especialidadeId,
    MedicoDTO medico,
    PacienteDTO paciente,
    EspecialidadeDTO especialidade,
    LocalDateTime dataConsulta,
    ConsultaStatus status
) {
    public ConsultaDTO(Long id, Long medicoId, Long pacienteId, Long especialidadeId, LocalDateTime dataConsulta, ConsultaStatus status) {
        this(id, medicoId, pacienteId, especialidadeId, null, null, null, dataConsulta, status);
    }
}