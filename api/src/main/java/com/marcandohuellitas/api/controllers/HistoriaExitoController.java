package com.marcandohuellitas.api.controllers;

import com.marcandohuellitas.api.models.HistoriaExito;
import com.marcandohuellitas.api.services.HistoriaExitoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controlador REST para HistoriaExito.
 * Recibe las peticiones HTTP del frontend.
 */
@RestController
@RequestMapping("/api/historias_exito")
public class HistoriaExitoController {

    @Autowired // Inyectamos el servicio
    private HistoriaExitoService service;

    @GetMapping // GET /api/historias_exito
    public List<HistoriaExito> listarTodos() {
        return service.obtenerTodos();
    }

    @GetMapping("/{id}") // GET /api/historias_exito/{id}
    public ResponseEntity<HistoriaExito> obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping // POST /api/historias_exito
    public HistoriaExito crear(@RequestBody HistoriaExito entidad) {
        return service.guardar(entidad);
    }

    @DeleteMapping("/{id}") // DELETE /api/historias_exito/{id}
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.ok().build();
    }
}
