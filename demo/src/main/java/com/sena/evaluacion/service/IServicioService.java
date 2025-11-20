package com.sena.evaluacion.service;

import java.util.List;
import java.util.Optional;

import com.sena.evaluacion.model.Servicio;

public interface IServicioService {
	
	Optional<Servicio> get(Integer id);
	
	Servicio findByNombre(String nombre);
	
	Servicio findByDuarion(String duracion);
	
	Servicio findByPrecio(Double precio);
	
	Servicio save(Servicio servicio);
	
	void delete(Integer id);
	
	Servicio update(Servicio servicio);
	
	List<Servicio> findAll();
	
	
	
	
	
}


