package com.sena.evaluacion.service;

import java.util.List;
import java.util.Optional;

import com.sena.evaluacion.model.Cita;

public interface ICitaService {
	
	Optional<Cita> get(Integer id);
	
	Cita findByEstado(String estado);
	
	Cita save(Cita cita);
	
	void delete(Integer id);
	
	Cita update(Cita cita);
	
	List<Cita> findAll();

}
