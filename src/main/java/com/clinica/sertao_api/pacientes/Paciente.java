package com.clinica.sertao_api.pacientes;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

import com.clinica.sertao_api.pacientes.enderecos.Endereco;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "paciente")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_paciente")
    private Long id;

    @NotBlank
    @Size(max = 150)
    @Column(nullable = false, length = 150)
    private String nome;

    @NotBlank
    @Size(max = 14)
    @Column(nullable = false, unique = true, length = 14)
    private String cpf;

    @NotNull
    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @Size(max = 18)
    @Column(length = 18)
    private String telefone;

    @Email
    @Size(max = 100)
    @Column(length = 100)
    private String email;

    @OneToOne(mappedBy = "paciente", cascade = CascadeType.ALL, orphanRemoval = true)
    private Endereco endereco;

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
        if (endereco != null) {
            endereco.setPaciente(this);
        }
    }
} 
    
