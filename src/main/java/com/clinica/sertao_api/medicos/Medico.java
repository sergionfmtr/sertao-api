package com.clinica.sertao_api.medicos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
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

    public Medico(MedicoDTO dto) {
        this.id = dto.getId();
        this.nome = dto.getNome();
        this.crm = dto.getCrm();
        this.telefone = dto.getTelefone();
        this.email = dto.getEmail();
    }
}