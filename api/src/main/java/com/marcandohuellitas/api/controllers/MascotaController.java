package com.marcandohuellitas.api.controllers;

import com.marcandohuellitas.api.models.Mascota;
import com.marcandohuellitas.api.services.MascotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controlador REST para Mascota.
 * Recibe las peticiones HTTP del frontend.
 */
@RestController
@RequestMapping("/api/mascotas")
public class MascotaController {

    @Autowired // Inyectamos el servicio
    private MascotaService service;

    @GetMapping // GET /api/mascotas
    public List<Mascota> listarTodos() {
        return service.obtenerTodos();
    }

    @GetMapping("/{id}") // GET /api/mascotas/{id}
    public ResponseEntity<Mascota> obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping // POST /api/mascotas
    public Mascota crear(@RequestBody Mascota entidad) {

        return service.guardar(entidad);
    }

    @PutMapping("/{id}") // PUT /api/mascotas/{id}
    public ResponseEntity<Mascota> actualizar(@PathVariable Long id, @RequestBody Mascota entidad) {
        return service.obtenerPorId(id)
                .map(mascotaExistente -> {
                    entidad.setId(id);
                    return ResponseEntity.ok(service.guardar(entidad));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}") // DELETE /api/mascotas/{id}
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.ok().build();
    }

}
