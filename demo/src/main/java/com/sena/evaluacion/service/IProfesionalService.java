package com.sena.evaluacion.service;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import com.sena.evaluacion.model.Profesional;

public interface IProfesionalService {
	
	Optional<Profesional> get(Integer id);
	
	Profesional findByEspecialidad(String especialidad);
	
	Profesional save(Profesional profesional);
	
	void delete(Integer id);
	
	Profesional update(Profesional profesional);
	
	List<Profesional> findAll();

}
