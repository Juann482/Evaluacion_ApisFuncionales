package com.sena.evaluacion.service;

import java.time.DateTimeException;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.evaluacion.model.Profesional;
import com.sena.evaluacion.model.Usuario;
import com.sena.evaluacion.repository.ProfesionalRepository;

@Service
public class ProfesionalServiceImplement implements IProfesionalService{
	
	@Autowired
	private ProfesionalRepository profesionalRepository;

	@Override
	public Optional<Profesional> get(Integer id) {
		return profesionalRepository.findById(id);
	}

	@Override
	public Profesional findByEspecialidad(String especialidad) {
		return profesionalRepository.findByEspecialidad(especialidad);
	}

	//@Override
	//public Profesional findByHorarioDisponible(LocalTime horarioDisponible) {
	//	return profesionalRepository.findByHorarioDisponible(horarioDisponible);
	//}

	@Override
	public Profesional save(Profesional profesional) {
		return profesionalRepository.save(profesional);
	}

	@Override
	public void delete(Integer id) {
		profesionalRepository.deleteById(id);
	}

	@Override
	public Profesional update(Profesional profesional) {
		return profesionalRepository.save(profesional);
	}

	@Override
	public List<Profesional> findAll() {
		return profesionalRepository.findAll();
	}

}