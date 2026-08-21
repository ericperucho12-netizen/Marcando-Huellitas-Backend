package com.marcandohuellitas.api.controllers;

import com.marcandohuellitas.api.models.SolicitudesApoyo;
import com.marcandohuellitas.api.services.SolicitudesApoyoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controlador REST para SolicitudesApoyo.
 * Recibe las peticiones HTTP del frontend.
 */
@RestController
@RequestMapping("/api/solicitudes_apoyo")
public class SolicitudesApoyoController {

    @Autowired // Inyectamos el servicio
    private SolicitudesApoyoService service;

    @GetMapping // GET /api/solicitudes_apoyo
    public List<SolicitudesApoyo> listarTodos() {
        return service.obtenerTodos();
    }

    @GetMapping("/{id}") // GET /api/solicitudes_apoyo/{id}
    public ResponseEntity<SolicitudesApoyo> obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping // POST /api/solicitudes_apoyo
    public SolicitudesApoyo crear(@RequestBody SolicitudesApoyo entidad) {
        return service.guardar(entidad);
    }

    @DeleteMapping("/{id}") // DELETE /api/solicitudes_apoyo/{id}
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.ok().build();
    }
}
