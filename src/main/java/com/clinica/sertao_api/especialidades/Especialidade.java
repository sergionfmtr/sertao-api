package com.clinica.sertao_api.especialidades;

import java.util.HashSet;
import java.util.Set;

import com.clinica.sertao_api.medicos.Medico;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Schema(description = "Representa uma especialidade médica")
@Table(name = "especialidade")
public class Especialidade {

    @Id
    @Column(name = "id_especialidade")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único da especialidade", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "Nome da especialidade", example = "Cardiologia")
    private String nome;

    @JsonIgnore
    @ManyToMany(mappedBy = "especialidades")
    private Set<Medico> medicos = new HashSet<>();
}