package com.sena.evaluacion.apis;

import java.util.Optional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.sena.evaluacion.model.Cita;
import com.sena.evaluacion.service.ICitaService;

@RestController
@RequestMapping("/api/citas")
public class ApiCita {

    @Autowired
    private ICitaService citaService;

    @GetMapping
    public ResponseEntity<List<Cita>> getAll() {
        return ResponseEntity.ok(citaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cita> getById(@PathVariable Integer id) {
        return citaService.get(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Cita> create(@RequestBody Cita cita) {
        Cita nueva = citaService.save(cita);
        return ResponseEntity.status(HttpStatus.CREATED).body(nueva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cita> update(@PathVariable Integer id, @RequestBody Cita cita) {
        return citaService.get(id)
                .map(existente -> {
                    cita.setId(id);
                    Cita actualizada = citaService.update(cita);
                    return ResponseEntity.ok(actualizada);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        return citaService.get(id)
                .map(c -> {
                    citaService.delete(id);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
