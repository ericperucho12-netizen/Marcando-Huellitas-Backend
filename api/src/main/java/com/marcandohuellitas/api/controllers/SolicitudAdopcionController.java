package com.marcandohuellitas.api.controllers;

import com.marcandohuellitas.api.models.SolicitudAdopcion;
import com.marcandohuellitas.api.services.SolicitudAdopcionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controlador REST para SolicitudAdopcion.
 * Recibe las peticiones HTTP del frontend.
 */
@RestController
@RequestMapping("/api/solicitudes_adopcion")
public class SolicitudAdopcionController {

    @Autowired // Inyectamos el servicio
    private SolicitudAdopcionService service;

    @GetMapping // GET /api/solicitudes_adopcion
    public List<SolicitudAdopcion> listarTodos() {
        return service.obtenerTodos();
    }

    @GetMapping("/{id}") // GET /api/solicitudes_adopcion/{id}
    public ResponseEntity<SolicitudAdopcion> obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping // POST /api/solicitudes_adopcion
    public SolicitudAdopcion crear(@RequestBody SolicitudAdopcion entidad) {
        return service.guardar(entidad);
    }

    @PutMapping("/{id}") // PUT /api/solicitudes_adopcion/{id}
    public ResponseEntity<SolicitudAdopcion> actualizar(@PathVariable Long id, @RequestBody SolicitudAdopcion entidad) {
        return service.obtenerPorId(id)
                .map(existente -> {
                    entidad.setId(id);
                    return ResponseEntity.ok(service.guardar(entidad));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}") // DELETE /api/solicitudes_adopcion/{id}
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.ok().build();
    }
}

