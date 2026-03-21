package com.clinica.sertao_api.medicos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Integer> {

    @Query("SELECT DISTINCT m FROM Medico m LEFT JOIN FETCH m.especialidades")
    List<Medico> findAllWithEspecialidades();

    @Query("SELECT DISTINCT m FROM Medico m LEFT JOIN FETCH m.especialidades WHERE m.id IN (SELECT m2.id FROM Medico m2 JOIN m2.especialidades e WHERE e.id = :especialidadeId)")
    List<Medico> findByEspecialidadeId(@Param("especialidadeId") Long especialidadeId);

    @Query("SELECT DISTINCT m FROM Medico m LEFT JOIN FETCH m.especialidades WHERE LOWER(m.nome) LIKE LOWER(CONCAT('%', :query, '%')) OR LOWER(m.crm) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<Medico> searchByNomeOrCrm(@Param("query") String query);
}