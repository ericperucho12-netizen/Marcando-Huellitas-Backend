package com.marcandohuellitas.api.controllers;

import com.marcandohuellitas.api.models.MascotasFavorita;
import com.marcandohuellitas.api.services.MascotasFavoritaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controlador REST para MascotasFavorita.
 * Recibe las peticiones HTTP del frontend.
 */
@RestController
@RequestMapping("/api/mascotas_favoritas")
public class MascotasFavoritaController {

    @Autowired // Inyectamos el servicio
    private MascotasFavoritaService service;

    @GetMapping // GET /api/mascotas_favoritas
    public List<MascotasFavorita> listarTodos() {
        return service.obtenerTodos();
    }

    @GetMapping("/{id}") // GET /api/mascotas_favoritas/{id}
    public ResponseEntity<MascotasFavorita> obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping // POST /api/mascotas_favoritas
    public MascotasFavorita crear(@RequestBody MascotasFavorita entidad) {
        return service.guardar(entidad);
    }

    @DeleteMapping("/{id}") // DELETE /api/mascotas_favoritas/{id}
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.ok().build();
    }
}
