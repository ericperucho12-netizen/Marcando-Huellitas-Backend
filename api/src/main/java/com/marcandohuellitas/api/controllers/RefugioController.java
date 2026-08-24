package com.marcandohuellitas.api.controllers;

import com.marcandohuellitas.api.models.Refugio;
import com.marcandohuellitas.api.services.RefugioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controlador REST para Refugio.
 * Recibe las peticiones HTTP del frontend.
 */
@RestController
@RequestMapping("/api/refugios")
public class RefugioController {

    @Autowired // Inyectamos el servicio
    private RefugioService service;

    @GetMapping // GET /api/refugios
    public List<Refugio> listarTodos() {
        return service.obtenerTodos();
    }

    @GetMapping("/{id}") // GET /api/refugios/{id}
    public ResponseEntity<Refugio> obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping // POST /api/refugios
    public Refugio crear(@RequestBody Refugio entidad) {
        return service.guardar(entidad);
    }

    @DeleteMapping("/{id}") // DELETE /api/refugios/{id}
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.ok().build();
    }
}
