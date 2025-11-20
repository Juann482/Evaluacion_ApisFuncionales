package com.sena.evaluacion.apis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.sena.evaluacion.model.Servicio;
import com.sena.evaluacion.service.IServicioService;

@RestController
@RequestMapping("/api/servicios")
public class ApiServicios {

    @Autowired
    private IServicioService servicioService;

    @GetMapping
    public ResponseEntity<List<Servicio>> getAll() {
        return ResponseEntity.ok(servicioService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Servicio> getById(@PathVariable Integer id) {
        return servicioService.get(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Servicio> create(@RequestBody Servicio servicio) {
        Servicio nuevo = servicioService.save(servicio);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Servicio> update(@PathVariable Integer id, @RequestBody Servicio servicio) {
        return servicioService.get(id)
                .map(existente -> {
                    servicio.setId(id);
                    Servicio actualizado = servicioService.update(servicio);
                    return ResponseEntity.ok(actualizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        return servicioService.get(id)
                .map(s -> {
                    servicioService.delete(id);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
