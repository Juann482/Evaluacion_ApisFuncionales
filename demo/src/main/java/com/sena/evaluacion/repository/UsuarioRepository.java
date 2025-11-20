package com.sena.evaluacion.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sena.evaluacion.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

	Usuario findByNombre(String nombre);

	Optional<Usuario>  findByEmail(String email);

	Usuario findByTelefono(String telefono);

}
