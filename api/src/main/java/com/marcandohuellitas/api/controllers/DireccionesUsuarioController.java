package com.marcandohuellitas.api.controllers;

import com.marcandohuellitas.api.models.DireccionesUsuario;
import com.marcandohuellitas.api.services.DireccionesUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controlador REST para DireccionesUsuario.
 * Recibe las peticiones HTTP del frontend.
 */
@RestController
@RequestMapping("/api/direcciones_usuario")
public class DireccionesUsuarioController {

    @Autowired // Inyectamos el servicio
    private DireccionesUsuarioService service;

    @GetMapping // GET /api/direcciones_usuario
    public List<DireccionesUsuario> listarTodos() {
        return service.obtenerTodos();
    }

    @GetMapping("/{id}") // GET /api/direcciones_usuario/{id}
    public ResponseEntity<DireccionesUsuario> obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping // POST /api/direcciones_usuario
    public DireccionesUsuario crear(@RequestBody DireccionesUsuario entidad) {
        return service.guardar(entidad);
    }

    @DeleteMapping("/{id}") // DELETE /api/direcciones_usuario/{id}
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.ok().build();
    }
}
