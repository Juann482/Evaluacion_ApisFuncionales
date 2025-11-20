package com.sena.evaluacion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sena.evaluacion.model.Cita;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Integer>{

	Cita findByEstado(String estado);

}
