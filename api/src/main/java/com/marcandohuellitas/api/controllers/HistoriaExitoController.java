package com.marcandohuellitas.api.controllers;

import com.marcandohuellitas.api.models.HistoriaExito;
import com.marcandohuellitas.api.services.HistoriaExitoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

/**
 * Controlador REST para HistoriaExito.
 * Recibe las peticiones HTTP del frontend.
 */
@RestController
@RequestMapping("/api/historias_exito")
@CrossOrigin(origins = "*") // Soluciona error CORS para permitir peticiones desde el frontend
public class HistoriaExitoController {

    @Autowired // Inyectamos el servicio
    private HistoriaExitoService service;

    // Obtener SOLO las historias aprobadas (Público)
    @GetMapping 
    public List<HistoriaExito> listarAprobadas() {
        return service.obtenerAprobados();
    }

    // Obtener TODAS las historias (Para panel de admin)
    @GetMapping("/admin")
    public List<HistoriaExito> listarTodas() {
        return service.obtenerTodos();
    }

    @GetMapping("/{id}") // GET /api/historias_exito/{id}
    public ResponseEntity<HistoriaExito> obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear historia (queda PENDIENTE por defecto)
    @PostMapping 
    public HistoriaExito crear(@RequestBody HistoriaExito entidad) {
        return service.guardar(entidad);
    }

    // Editar historia completa (Para admin)
    @PutMapping("/{id}")
    public ResponseEntity<HistoriaExito> editarHistoria(@PathVariable Long id, @RequestBody HistoriaExito historiaEditada) {
        HistoriaExito actualizada = service.editarHistoria(id, historiaEditada);
        if (actualizada != null) {
            return ResponseEntity.ok(actualizada);
        }
        return ResponseEntity.notFound().build();
    }

    // Aprobar o rechazar historia (Para panel de admin)
    @PutMapping("/{id}/estado")
    public ResponseEntity<HistoriaExito> actualizarEstado(@PathVariable Long id, @RequestBody Map<String, String> request) {
        String estado = request.get("estado");
        if (estado == null) {
            return ResponseEntity.badRequest().build();
        }
        HistoriaExito actualizada = service.actualizarEstado(id, estado);
        if (actualizada != null) {
            return ResponseEntity.ok(actualizada);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}") // DELETE /api/historias_exito/{id}
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.ok().build();
    }
}