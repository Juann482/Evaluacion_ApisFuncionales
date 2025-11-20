package com.sena.evaluacion.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.evaluacion.model.Usuario;
import com.sena.evaluacion.repository.UsuarioRepository;

@Service
public class UsuarioServiceImplement implements IUsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Optional<Usuario> get(Integer id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public Usuario findByNombre(String nombre) {
        return usuarioRepository.findByNombre(nombre);
    }

    @Override
    public Optional<Usuario> findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    @Override
    public Usuario findByTelefono(String telefono) {
        return usuarioRepository.findByTelefono(telefono);
    }

    @Override
    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public void delete(Integer id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public Optional<Usuario> findById(Integer id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public Usuario update(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }
}
