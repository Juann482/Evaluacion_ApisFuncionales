package com.sena.evaluacion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sena.evaluacion.model.Servicio;

@Repository
public interface ServicioRepository extends JpaRepository<Servicio, Integer> {

	Servicio findByNombre(String nombre);

	Servicio findByDuracion(String duracion);

	Servicio findByPrecio(Double precio);

}
