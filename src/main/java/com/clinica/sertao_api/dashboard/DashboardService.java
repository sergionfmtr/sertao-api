package com.clinica.sertao_api.dashboard;

import com.clinica.sertao_api.consultas.ConsultaDTO;
import com.clinica.sertao_api.consultas.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class DashboardService {

    @Autowired
    private ConsultaService consultaService;

    public DashboardResponse getResumoDoMes() {
        YearMonth mesAtual = YearMonth.now();
        LocalDateTime inicioDoMes = mesAtual.atDay(1).atStartOfDay();
        LocalDateTime fimDoMes = mesAtual.atEndOfMonth().atTime(23, 59, 59);

        var consultas = consultaService.findAll(null, null, null, inicioDoMes, fimDoMes);

        long quantidadeConsultas = consultas.size();
        
        long pacientesAtendidos = consultas.stream()
                .map(consulta -> consulta.pacienteId()) // Ajuste se o seu DTO usar outro formato, como consulta.paciente().id() ou consulta.getPacienteId()
                .distinct()
                .count();

        LocalDateTime agora = LocalDateTime.now();
        long consultasPendentes = consultas.stream()
                .filter(consulta -> consulta.dataConsulta() != null && consulta.dataConsulta().isAfter(agora)) // Ajuste se o seu DTO usar getDataConsulta()
                .count();

        return new DashboardResponse(quantidadeConsultas, pacientesAtendidos, consultasPendentes);
    }

    public List<ConsultaDTO> getUltimosAgendamentos() {
        var todasConsultas = new ArrayList<>(consultaService.findAll(null, null, null, null, null));
        Collections.reverse(todasConsultas);
        return todasConsultas.stream().limit(10).toList();
    }
}