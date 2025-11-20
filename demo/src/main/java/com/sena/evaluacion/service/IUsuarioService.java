package com.sena.evaluacion.service;

import java.util.List;
import java.util.Optional;

import com.sena.evaluacion.model.Usuario;

public interface IUsuarioService {
	
	Optional<Usuario> get(Integer id);
	
	Usuario findByNombre(String nombre);
	
	Optional<Usuario> findByEmail(String email);
	
	Usuario findByTelefono(String telefono);
	
	Usuario save(Usuario usuario);
	
	void delete(Integer id);
	
	Usuario update(Usuario usuario);
	
	List<Usuario> findAll();

	Optional<Usuario> findById(Integer id);
}
