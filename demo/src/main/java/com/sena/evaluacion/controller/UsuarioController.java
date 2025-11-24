package com.sena.evaluacion.controller;

import java.time.LocalDateTime;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.sena.evaluacion.model.Cita;
import com.sena.evaluacion.model.Profesional;
import com.sena.evaluacion.model.Servicio;
import com.sena.evaluacion.model.Usuario;
import com.sena.evaluacion.service.ICitaService;
import com.sena.evaluacion.service.IProfesionalService;
import com.sena.evaluacion.service.IServicioService;
import com.sena.evaluacion.service.IUsuarioService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

	private final Logger LOGGER = LoggerFactory.getLogger(UsuarioController.class);

	@Autowired
	private IUsuarioService usuarioService;

	@Autowired
	private IProfesionalService profesionalService;

	@Autowired
	private IServicioService servicioService;

	@Autowired
	private ICitaService citaService;

	BCryptPasswordEncoder pe = new BCryptPasswordEncoder();

	// ====================== Registro de usuarios =======================

	@GetMapping("/RegistroU")
	public String GuardarUsuario(Model model) {

		model.addAttribute("CrearUSER", usuarioService.findAll());
		model.addAttribute("especializacion", profesionalService.findAll());

		return "usuario/RegistroUSER";
	}

	@PostMapping("/GuardarUSER")
	public String EnviarnewUSER(@ModelAttribute Usuario usuario) {
		usuario.setPassword(pe.encode(usuario.getPassword()));
		usuario.setFecha_registro(LocalDateTime.now());
		usuarioService.save(usuario);
		LOGGER.info("Usuario guardado con éxito: {}", usuario.getNombre());
		return "redirect:/usuario/HistorialU";
	}

	// ============================ Registro de especialización
	// ==========================

	@GetMapping("/RegistroESPE")
	public String GuardarESPE(Model model) {
		model.addAttribute("especialidad", profesionalService.findAll());
		return "usuario/RegistroE";
	}

	@PostMapping("/GuardarESPE")
	public String EnviarNewEspe(@ModelAttribute Profesional profesion, HttpSession session) {

		Integer idUser = (Integer) session.getAttribute("idUsuario");

		Usuario user = usuarioService.findById(idUser).get();

		profesion.setUsuario(user);
		profesionalService.save(profesion);
		LOGGER.info("Especialidad registrada con éxito: {}", profesion);
		return "redirect:/usuario/HistorialE";
	}

	// ======================== Registro de servicios ==============================

	@GetMapping("/RegistroSERV")
	public String GuardarNewService(Model model) {
		model.addAttribute("servicio", new Servicio());
		return "usuario/RegistroSERVICIO";
	}

	@PostMapping("/GuardarSERV")
	public String EnviarNewSERVER(@ModelAttribute Servicio servicio) {
		servicioService.save(servicio);
		LOGGER.info("Nuevo servicio guardado con éxito: {}", servicio);
		return "redirect:/usuario/HistorialSERV";
	}

	// ========================== Historial de usuarios ========================

	@GetMapping("/HistorialU")
	public String HistorialUs(Model model) {
		model.addAttribute("Usuarios", usuarioService.findAll());
		return "usuario/HistorialUSER";
	}

	@GetMapping("/EditarUser/{id}")
	public String EditarUs(@PathVariable Integer id, Model model) {
		Usuario ue = usuarioService.get(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
		LOGGER.info("Usuario en edición: {}", ue);
		model.addAttribute("user", ue);
		model.addAttribute("especializacion", profesionalService.findAll());
		return "usuario/EdicionUSER";
	}

	@PostMapping("/SendEditU")
	public String SendUserEdit(@ModelAttribute("user") Usuario usuario) {
		Usuario existente = usuarioService.get(usuario.getId())
				.orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + usuario.getId()));

		// Actualizar solo los campos editables
		existente.setNombre(usuario.getNombre());
		existente.setEmail(usuario.getEmail());
		existente.setTelefono(usuario.getTelefono());
		existente.setProfesional(usuario.getProfesional());
		// ... otros campos editables

		usuarioService.update(existente);
		LOGGER.info("Usuario actualizado: {}", existente);
		return "redirect:/usuario/HistorialU";
	}

	@GetMapping("/DeleteUser/{id}")
	public String DeleteUs(@PathVariable Integer id) {
		Usuario u = usuarioService.get(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
		usuarioService.delete(id);
		LOGGER.warn("Usuario eliminado: {}", u);
		return "redirect:/usuario/HistorialU";
	}

	// ================== Historial de especialidades ===============

	@GetMapping("/HistorialE")
	public String HistorialESPE(Usuario usuario, Model model) {

		model.addAttribute("especialidades", profesionalService.findAll());
		// model.addAttribute("especializacion", profesionalService.findAll());
		return "usuario/HistorialESPE";
	}

	@GetMapping("/EditarES/{id}")
	public String EditarEspecialidad(@PathVariable Integer id, Profesional profesion, Model model) {

		Profesional p = new Profesional();
		Optional<Profesional> pt = profesionalService.get(id);
		p = pt.get();
		model.addAttribute("espe", p);
		return "usuario/EdicionE";
	}

	@PostMapping("/SendNewE")
	public String SendEspeEdit(Profesional profesion) {

		Profesional especialidad = profesionalService.get(profesion.getId())
				.orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + profesion.getId()));

		especialidad.setEspecialidad(profesion.getEspecialidad());
		especialidad.setHorario_disponible(profesion.getHorario_disponible());

		profesionalService.update(especialidad);

		LOGGER.warn("Especialidad actualizada con exito: {}", especialidad);

		return "redirect:/usuario/HistorialE";
	}

	@GetMapping("/DeleteEspe/{id}")
	public String DeleteEs(@PathVariable Integer id) {
		Profesional u = profesionalService.get(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
		profesionalService.delete(id);
		LOGGER.warn("Especialización eliminado: {}", u);
		return "redirect:/usuario/HistorialE";
	}

	// ======================== Historial de servicios ====================

	@GetMapping("/HistorialSERV")
	public String HistorialService(Servicio servicio, Model model) {

		model.addAttribute("Servicios", servicioService.findAll());

		return "usuario/HistorialSERVICIO";
	}

	@GetMapping("/EditSERV/{id}")
	public String EditServ(@PathVariable Integer id, Servicio servicio, Model model) {

		Servicio p = new Servicio();
		Optional<Servicio> pt = servicioService.get(id);
		p = pt.get();
		model.addAttribute("serv", p);
		return "usuario/EdicionSERVICIO";
	}

	@PostMapping("/SendEdit")
	public String SendServEdit(Servicio servicio) {
		Servicio s = servicioService.get(servicio.getId())
				.orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + servicio.getId()));

		s.setNombre(servicio.getNombre());
		s.setDuracion(servicio.getDuracion());
		s.setPrecio(servicio.getPrecio());
		s.setDescripcion(servicio.getDescripcion());
		servicioService.update(s);
		LOGGER.warn("Servicio actualizado: {}", s);
		return "redirect:/usuario/HistorialSERV";
	}

	@GetMapping("/DeleteServ/{id}")
	public String DeleteSer(@PathVariable Integer id) {
		Servicio u = servicioService.get(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
		servicioService.delete(id);
		LOGGER.warn("Servicio eliminado: {}", u);
		return "redirect:/usuario/HistorialSERV";
	}

	// ==================== Asignacion de cita ====================

	@GetMapping("/RegistroCT")
	public String RegistroCITA(Cita cita, Model model) {

		model.addAttribute("usuarios", usuarioService.findAll());
		model.addAttribute("servicios", servicioService.findAll());
		model.addAttribute("especializacion", profesionalService.findAll());
		model.addAttribute("cita", new Cita());

		return "usuario/RegistroCITA";
	}

	@GetMapping("/cita/toggleEstado/{id}")
	public String toggleEstado(@PathVariable Integer id) {

		Cita cita = citaService.get(id).orElse(null);

		if (cita != null) {
			if ("Activo".equalsIgnoreCase(cita.getEstado())) {
				cita.setEstado("Inactivo");
			} else {
				cita.setEstado("Activo");
			}

			citaService.save(cita);
		}

		return "redirect:/usuario/historialC";
	}

	@PostMapping("/NewCita")
	public String SendNewCita(Cita cita, HttpSession session) {

		Integer idUser = (Integer) session.getAttribute("idUsuario");

		Usuario user = usuarioService.findById(idUser).get();

		cita.setUsuario(user);
		cita.setEstado("Activo");
		citaService.save(cita);
		LOGGER.warn("Nueva cita programada: {}", cita);
		return "redirect:/usuario/historialC";
	}

	// ========================== Historial de citas ========================

	@GetMapping("/historialC")
	public String HistorialCT(Model model) {
		model.addAttribute("citas", citaService.findAll());
		return "usuario/HistorialCITA";
	}

	@GetMapping("/EditCita/{id}")
	public String EditarCita(@PathVariable Integer id, Cita cita, Model model) {

		Cita csm = new Cita();
		Optional<Cita> pt = citaService.get(id);
		csm = pt.get();
		model.addAttribute("Ok", csm);
		model.addAttribute("servicios", servicioService.findAll());
		model.addAttribute("usuarios", usuarioService.findAll());
		model.addAttribute("especializacion", profesionalService.findAll());
		return "usuario/EdicionCITA";
	}

	@PostMapping("/SendActCit")
	public String SendCitaAct(Cita cita) {

		Cita c = citaService.get(cita.getId())
				.orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + cita.getId()));
		cita.setEstado("Activo");
		c.setEstado(cita.getEstado());
		c.setFecha_hora(cita.getFecha_hora());
		c.setProfesional(cita.getProfesional());
		c.setUsuario(cita.getUsuario());
		c.setServicio(cita.getServicio());

		citaService.save(c);
		LOGGER.warn("Cita actualizada con exito: {}", c);
		return "redirect:/usuario/historialC";
	}

	@GetMapping("/DeleteCita/{id}")
	public String DeleteCita(@PathVariable Integer id) {
		Cita u = citaService.get(id).orElseThrow(() -> new RuntimeException("Cita no encontrado"));
		citaService.delete(id);
		LOGGER.warn("Cita eliminada: {}", u);
		return "redirect:/usuario/historialC";
	}
}
