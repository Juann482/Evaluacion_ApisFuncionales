package com.sena.evaluacion.repository;

import java.time.LocalTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sena.evaluacion.model.Profesional;

@Repository
public interface ProfesionalRepository extends JpaRepository<Profesional, Integer> {

	Profesional findByEspecialidad(String especialidad);

}
