package com.sena.evaluacion.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String nombre;
	
	private String email;
	
	private String password;
	
	private String telefono;
	
	private LocalDateTime fecha_registro;
	
	private String rol;
	
	@ManyToOne
	@JoinColumn(nullable = true)
	private Profesional profesional;

	@OneToMany(mappedBy = "usuario")
	private List<Cita> cita = new ArrayList<>();

	public Usuario() {}

	public Usuario(Integer id, String nombre, String email, String password, String telefono,
			LocalDateTime fecha_registro, Profesional profesional, List<Cita> cita, String rol) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.email = email;
		this.password = password;
		this.telefono = telefono;
		this.fecha_registro = fecha_registro;
		this.profesional = profesional;
		this.cita = cita;
		this.rol = rol;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public LocalDateTime getFecha_registro() {
		return fecha_registro;
	}

	public void setFecha_registro(LocalDateTime fecha_registro) {
		this.fecha_registro = fecha_registro;
	}

	public Profesional getProfesional() {
		return profesional;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public void setProfesional(Profesional profesional) {
		this.profesional = profesional;
	}

	public List<Cita> getCita() {
		return cita;
	}

	public void setCita(List<Cita> cita) {
		this.cita = cita;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", email=" + email + ", password=" + password
				+ ", telefono=" + telefono + ", fecha_registro=" + fecha_registro + ", profesional=" + profesional
				+ ", cita=" + cita + "]";
	}
	
	
	
	
}
