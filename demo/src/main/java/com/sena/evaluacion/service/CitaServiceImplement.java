package com.sena.evaluacion.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.evaluacion.model.Cita;
import com.sena.evaluacion.model.Usuario;
import com.sena.evaluacion.repository.CitaRepository;

@Service
public class CitaServiceImplement implements ICitaService{
	
	@Autowired
	private CitaRepository citaRepository;

	@Override
	public Optional<Cita> get(Integer id) {
		return citaRepository.findById(id);
	}

	@Override
	public Cita findByEstado(String estado) {
		return citaRepository.findByEstado(estado);
	}

	@Override
	public Cita save(Cita cita) {
		return citaRepository.save(cita);
	}

	@Override
	public void delete(Integer id) {
		citaRepository.deleteById(id);
	}

	@Override
	public Cita update(Cita cita) {
		return citaRepository.save(cita);
	}

	@Override
	public List<Cita> findAll() {
		return citaRepository.findAll();
	}

}
