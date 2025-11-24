package com.sena.evaluacion.security;

import java.io.IOException;
import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.sena.evaluacion.model.Usuario;
import com.sena.evaluacion.service.IUsuarioService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class CustomSuccessHandler implements AuthenticationSuccessHandler {

	private final IUsuarioService usuarioService;
	
	 public CustomSuccessHandler(IUsuarioService usuarioService) {
	        this.usuarioService = usuarioService;
	    }
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		String correo = authentication.getName();
		
		usuarioService.findByEmail(correo).ifPresent(user ->{
			HttpSession session = request.getSession();
			session.setAttribute("Nombre", user.getNombre());
			session.setAttribute("idUsuario", user.getId());

		});
		
		response.sendRedirect("/usuario/HistorialU");
	}
}
