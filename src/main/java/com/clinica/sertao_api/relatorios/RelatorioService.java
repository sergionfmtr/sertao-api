package com.clinica.sertao_api.relatorios;

import com.clinica.sertao_api.consultas.Consulta;
import com.clinica.sertao_api.consultas.ConsultaRepository;
import com.clinica.sertao_api.pacientes.Paciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class RelatorioService {

    @Autowired
    private ConsultaRepository consultaRepository;

    public List<LabelValueDTO> getConsultasPorEspecialidade() {
        return consultaRepository.findAll().stream()
                .filter(c -> c.getEspecialidade() != null && c.getEspecialidade().getNome() != null)
                .collect(Collectors.groupingBy(c -> c.getEspecialidade().getNome(), Collectors.counting()))
                .entrySet().stream()
                .map(e -> new LabelValueDTO(e.getKey(), e.getValue()))
                .sorted((a, b) -> b.value().compareTo(a.value()))
                .toList();
    }

    public List<LabelValueDTO> getStatusConsultas() {
        return consultaRepository.findAll().stream()
                .filter(c -> c.getStatus() != null)
                .collect(Collectors.groupingBy(c -> c.getStatus().name(), Collectors.counting()))
                .entrySet().stream()
                .map(e -> new LabelValueDTO(e.getKey(), e.getValue()))
                .sorted((a, b) -> b.value().compareTo(a.value()))
                .toList();
    }

    public List<LabelValueDTO> getEvolucaoMensal() {
        YearMonth currentMonth = YearMonth.now();
        LocalDateTime startOfMonth = currentMonth.atDay(1).atStartOfDay();
        LocalDateTime endOfMonth = currentMonth.atEndOfMonth().atTime(23, 59, 59);

        Map<Integer, Long> contagemPorDia = consultaRepository.findAll().stream()
                .filter(c -> c.getDataConsulta() != null)
                .filter(c -> !c.getDataConsulta().isBefore(startOfMonth) && !c.getDataConsulta().isAfter(endOfMonth))
                .collect(Collectors.groupingBy(c -> c.getDataConsulta().getDayOfMonth(), Collectors.counting()));

        // IntStream garante que dias sem consulta retornem valor 0, preenchendo o mês atual completo
        return IntStream.rangeClosed(1, currentMonth.lengthOfMonth())
                .mapToObj(dia -> new LabelValueDTO(
                        String.format("%02d/%02d", dia, currentMonth.getMonthValue()),
                        contagemPorDia.getOrDefault(dia, 0L)
                ))
                .toList();
    }

    public List<LabelValueDTO> getTopMedicos() {
        return consultaRepository.findAll().stream()
                .filter(c -> c.getMedico() != null && c.getMedico().getNome() != null)
                .collect(Collectors.groupingBy(c -> c.getMedico().getNome(), Collectors.counting()))
                .entrySet().stream()
                .map(e -> new LabelValueDTO(e.getKey(), e.getValue()))
                .sorted((a, b) -> b.value().compareTo(a.value())) // Ordenação decrescente
                .limit(5) // Top 5
                .toList();
    }

    public List<LabelValueDTO> getPacientesPorFaixaEtaria() {
        List<Consulta> consultas = consultaRepository.findAll();

        // Isolar pacientes únicos através das consultas
        Set<Paciente> pacientes = consultas.stream()
                .map(Consulta::getPaciente)
                .filter(Objects::nonNull)
                .filter(p -> p.getDataNascimento() != null)
                .collect(Collectors.toSet());

        long kids = 0, adultos = 0, idosos = 0;
        LocalDate hoje = LocalDate.now();

        for (Paciente p : pacientes) {
            long idade = ChronoUnit.YEARS.between(p.getDataNascimento(), hoje);
            if (idade < 18) {
                kids++;
            } else if (idade < 60) {
                adultos++;
            } else {
                idosos++;
            }
        }

        return List.of(
                new LabelValueDTO("Kids (0-17)", kids),
                new LabelValueDTO("Adultos (18-59)", adultos),
                new LabelValueDTO("Idosos (60+)", idosos)
        );
    }

    public List<LabelValueDTO> getConsultasPorDiaSemana() {
        return consultaRepository.findAll().stream()
                .filter(c -> c.getDataConsulta() != null)
                .collect(Collectors.groupingBy(
                        c -> c.getDataConsulta().getDayOfWeek().getDisplayName(TextStyle.FULL, new Locale("pt", "BR")),
                        Collectors.counting()
                ))
                .entrySet().stream()
                .map(e -> new LabelValueDTO(capitalize(e.getKey()), e.getValue()))
                .sorted((a, b) -> b.value().compareTo(a.value()))
                .toList();
    }

    /**
     * Utilitário para formatar dias da semana ("Segunda-feira", etc.)
     */
    private String capitalize(String str) {
        if (str == null || str.isEmpty()) return str;
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }
}