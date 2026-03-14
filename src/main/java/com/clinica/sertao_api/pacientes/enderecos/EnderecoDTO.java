package com.clinica.sertao_api.pacientes.enderecos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * Data Transfer Object para Endereço com validações rigorosas.
 */
public record EnderecoDTO(
    @NotBlank(message = "O CEP é obrigatório.")
    @Pattern(regexp = "\\d{5}-\\d{3}", message = "O CEP deve seguir o formato 00000-000.")
    String cep,

    @NotBlank(message = "O logradouro é obrigatório.")
    @Size(max = 150, message = "O logradouro não pode exceder 150 caracteres.")
    String logradouro,

    @Size(max = 10, message = "O número não pode exceder 10 caracteres.")
    String numero,

    @Size(max = 50, message = "O complemento não pode exceder 50 caracteres.")
    String complemento,

    @NotBlank(message = "O bairro é obrigatório.")
    @Size(max = 150, message = "O bairro não pode exceder 150 caracteres.")
    String bairro,

    @NotBlank(message = "A cidade é obrigatória.")
    @Size(max = 30, message = "A cidade não pode exceder 30 caracteres.")
    String cidade,

    @NotBlank(message = "O estado (UF) é obrigatório.")
    @Pattern(regexp = "[A-Z]{2}", message = "O estado deve conter exatamente 2 letras maiúsculas (ex: SP).")
    String estado
) {
    public EnderecoDTO(Endereco entity) {
        this(
            entity.getCep(),
            entity.getLogradouro(),
            entity.getNumero(),
            entity.getComplemento(),
            entity.getBairro(),
            entity.getCidade(),
            entity.getEstado()
        );
    }
}