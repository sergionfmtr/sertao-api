package com.clinica.sertao_api.pacientes.enderecos;


import com.clinica.sertao_api.pacientes.Paciente;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "endereco")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_endereco")
    private Long id;

    @NotBlank
    @Size(max = 9)
    @Column(nullable = false, length = 9)
    private String cep;

    @NotBlank
    @Size(max = 150)
    @Column(nullable = false, length = 150)
    private String logradouro;

    @Size(max = 10)
    @Column(length = 10)
    private String numero;

    @Size(max = 50)
    @Column(length = 50)
    private String complemento;

    @NotBlank
    @Size(max = 150)
    @Column(nullable = false, length = 150)
    private String bairro;

    @NotBlank
    @Size(max = 30)
    @Column(nullable = false, length = 30)
    private String cidade;

    @NotBlank
    @Size(max = 2)
    @Column(nullable = false, length = 2, columnDefinition = "char(2)")
    private String estado;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_paciente", referencedColumnName = "id_paciente", nullable = false)
    private Paciente paciente;
} 