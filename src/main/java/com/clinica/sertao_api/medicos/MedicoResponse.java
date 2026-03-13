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
            dto.id(),
            dto.nome(),
            dto.crm(),
            dto.telefone(),
            dto.email()
        );
    }

    public MedicoDTO toMedicoDTO() {
        return new MedicoDTO(
            this.id(),
            this.nome(),
            this.crm(),
            this.telefone(),
            this.email()
        );
    }
}