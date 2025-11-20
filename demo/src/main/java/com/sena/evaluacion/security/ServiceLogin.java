package com.sena.evaluacion.security;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sena.evaluacion.model.Usuario;
import com.sena.evaluacion.service.IUsuarioService;

import jakarta.servlet.http.HttpSession;

@Service
public class ServiceLogin implements UserDetailsService {

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private HttpSession session;

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
                    .authorities(present.getRol()) // usamos el rol tal cual en la DB
                    .build();
        } 
    }
