package com.sena.evaluacion.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.evaluacion.model.Servicio;
import com.sena.evaluacion.repository.ServicioRepository;

@Service
public class ServicioServiceImplement implements IServicioService{
	
	@Autowired
	private ServicioRepository servicioRepository;

	@Override
	public Optional<Servicio> get(Integer id) {
		return servicioRepository.findById(id);
	}

	@Override
	public Servicio findByNombre(String nombre) {
		return servicioRepository.findByNombre(nombre);
	}

	@Override
	public Servicio findByDuarion(String duracion) {
		return servicioRepository.findByDuracion(duracion);
	}

	@Override
	public Servicio findByPrecio(Double precio) {
		return servicioRepository.findByPrecio(precio);
	}

	@Override
	public Servicio save(Servicio servicio) {
		return servicioRepository.save(servicio);
	}

	@Override
	public void delete(Integer id) {
		servicioRepository.deleteById(id);
	}

	@Override
	public Servicio update(Servicio servicio) {
		return servicioRepository.save(servicio);
	}

	@Override
	public List<Servicio> findAll() {		
		return servicioRepository.findAll();
	}

}
