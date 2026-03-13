package com.clinica.sertao_api.medicos;

import java.util.HashSet;
import java.util.Set;

import com.clinica.sertao_api.especialidades.Especialidade;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "medico")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_medico")
    private Integer id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, length = 20, unique = true)
    private String crm;

    @Column(length = 20)
    private String telefone;

    @Column(length = 100, unique = true)
    private String email;

    @ManyToMany
    @JoinTable(
        name = "medico_especialidade",
        joinColumns = @JoinColumn(name = "id_medico"),
        inverseJoinColumns = @JoinColumn(name = "id_especialidade")
    )
    private Set<Especialidade> especialidades = new HashSet<>();

    public Medico(MedicoDTO dto) {
        this.id = dto.id();
        this.nome = dto.nome();
        this.crm = dto.crm();
        this.telefone = dto.telefone();
        this.email = dto.email();
    }
}