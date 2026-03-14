package com.clinica.sertao_api.medicos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Integer> {

    @Query("SELECT DISTINCT m FROM Medico m LEFT JOIN FETCH m.especialidades")
    List<Medico> findAllWithEspecialidades();
}