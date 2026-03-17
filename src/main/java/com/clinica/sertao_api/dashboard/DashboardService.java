package com.clinica.sertao_api.dashboard;

import com.clinica.sertao_api.consultas.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.YearMonth;

@Service
public class DashboardService {

    @Autowired
    private ConsultaService consultaService;

    public DashboardResponse getResumoDoMes() {
        YearMonth mesAtual = YearMonth.now();
        LocalDateTime inicioDoMes = mesAtual.atDay(1).atStartOfDay();
        LocalDateTime fimDoMes = mesAtual.atEndOfMonth().atTime(23, 59, 59);

        // Reutilizamos o método findAll existente no ConsultaService passando as datas do mês atual
        int quantidade = consultaService.findAll(null, null, null, inicioDoMes, fimDoMes).size();

        return new DashboardResponse((long) quantidade);
    }
}