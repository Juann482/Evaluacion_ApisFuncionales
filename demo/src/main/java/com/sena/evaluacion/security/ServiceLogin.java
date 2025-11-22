package com.sena.evaluacion.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sena.evaluacion.model.Usuario;
import com.sena.evaluacion.service.IUsuarioService;

@Service
public class ServiceLogin implements UserDetailsService {

    @Autowired
    private IUsuarioService usuarioService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
    	Optional<Usuario> user = usuarioService.findByEmail(username);

    	if (user.isEmpty()) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }   	
        
            Usuario present = user.get();

            return User.builder()
                    .username(present.getEmail())
                    .password(present.getPassword())
                    .build();
        } 
    }
