package com.sena.evaluacion.model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "profesional")
public class Profesional {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String especialidad;
	
	private LocalTime horario_disponible;
	
	@ManyToOne
	@JsonIgnore
	private Usuario usuario;
	
	@OneToMany(mappedBy = "profesional")
	@JsonIgnore
	private List<Cita> cita = new ArrayList<>();

	public Profesional() {}

	public Profesional(Integer id, String especialidad, LocalTime horario_disponible, Usuario usuario,
			List<Cita> cita) {
		super();
		this.id = id;
		this.especialidad = especialidad;
		this.horario_disponible = horario_disponible;
		this.usuario = usuario;
		this.cita = cita;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

	public LocalTime getHorario_disponible() {
		return horario_disponible;
	}

	public void setHorario_disponible(LocalTime horario_disponible) {
		this.horario_disponible = horario_disponible;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Cita> getCita() {
		return cita;
	}

	public void setCita(List<Cita> cita) {
		this.cita = cita;
	}

	@Override
	public String toString() {
		return "Profesional [id=" + id + ", especialidad=" + especialidad + ", horario_disponible=" + horario_disponible
				+ ", usuario=" + usuario + ", cita=" + cita + "]";
	}

	
}


