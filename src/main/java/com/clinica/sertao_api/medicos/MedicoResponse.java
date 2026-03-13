package com.clinica.sertao_api.medicos;

public record MedicoResponse(
    Integer id,
    String nome,
    String crm,
    String telefone,
    String email
) {
    public MedicoResponse(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getCrm(), medico.getTelefone(), medico.getEmail());
    }

    public static MedicoResponse toResponse(MedicoDTO dto) {
        if (dto == null) {
            return null;
        }
        return new MedicoResponse(
            dto.getId(),
            dto.getNome(),
            dto.getCrm(),
            dto.getTelefone(),
            dto.getEmail()
        );
    }

    public MedicoDTO toDTO() {
        // Assumindo que MedicoDTO possui um construtor padrão e setters, 
        // ou um construtor que aceita estes argumentos.
        // A implementação abaixo assume um DTO estilo Bean para compatibilidade geral.
        MedicoDTO dto = new MedicoDTO();
        dto.setId(this.id());
        dto.setNome(this.nome());
        dto.setCrm(this.crm());
        dto.setTelefone(this.telefone());
        dto.setEmail(this.email());
        return dto;
    }
}