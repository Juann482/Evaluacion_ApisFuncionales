package com.sena.evaluacion.apis;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sena.evaluacion.model.Profesional;
import com.sena.evaluacion.model.Usuario;
import com.sena.evaluacion.service.IProfesionalService;
import com.sena.evaluacion.service.IUsuarioService;

@RestController
@RequestMapping("/api/profesionales")
public class ApiProfesional {

    @Autowired
    private IProfesionalService profesionalService;

    @GetMapping
    public ResponseEntity<List<Profesional>> getAll() {
        return ResponseEntity.ok(profesionalService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Profesional> getById(@PathVariable Integer id) {
        return profesionalService.get(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Profesional> create(@RequestBody Profesional profesional) {
        Profesional creado = profesionalService.save(profesional);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Profesional> update(
            @PathVariable Integer id,
            @RequestBody Profesional profesional
    ) {
        return profesionalService.get(id)
                .map(actual -> {
                    actual.setEspecialidad(profesional.getEspecialidad());
                    actual.setCita(profesional.getCita());
                    Profesional actualizado = profesionalService.update(actual);
                    return ResponseEntity.ok(actualizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        return profesionalService.get(id)
                .map(p -> {
                    profesionalService.delete(id);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
