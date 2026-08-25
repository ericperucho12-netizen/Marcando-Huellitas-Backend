package com.marcandohuellitas.api.controllers;

import com.marcandohuellitas.api.models.Donacion;
import com.marcandohuellitas.api.services.DonacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controlador REST para Donacion.
 * Recibe las peticiones HTTP del frontend.
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/donaciones")
public class DonacionController {

    @Autowired // Inyectamos el servicio
    private DonacionService service;

    @GetMapping // GET /api/donaciones
    public List<Donacion> listarTodos() {
        return service.obtenerTodos();
    }

    @GetMapping("/{id}") // GET /api/donaciones/{id}
    public ResponseEntity<Donacion> obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping // POST /api/donaciones
    public Donacion crear(@RequestBody Donacion entidad) {
        return service.guardar(entidad);
    }

    @DeleteMapping("/{id}") // DELETE /api/donaciones/{id}
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.ok().build();
    }
}
