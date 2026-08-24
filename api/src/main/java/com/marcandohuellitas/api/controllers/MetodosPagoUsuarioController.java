package com.marcandohuellitas.api.controllers;

import com.marcandohuellitas.api.models.MetodosPagoUsuario;
import com.marcandohuellitas.api.services.MetodosPagoUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controlador REST para MetodosPagoUsuario.
 * Recibe las peticiones HTTP del frontend.
 */
@RestController
@RequestMapping("/api/metodos_pago_usuario")
public class MetodosPagoUsuarioController {

    @Autowired // Inyectamos el servicio
    private MetodosPagoUsuarioService service;

    @GetMapping // GET /api/metodos_pago_usuario
    public List<MetodosPagoUsuario> listarTodos() {
        return service.obtenerTodos();
    }

    @GetMapping("/{id}") // GET /api/metodos_pago_usuario/{id}
    public ResponseEntity<MetodosPagoUsuario> obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping // POST /api/metodos_pago_usuario
    public MetodosPagoUsuario crear(@RequestBody MetodosPagoUsuario entidad) {
        return service.guardar(entidad);
    }

    @DeleteMapping("/{id}") // DELETE /api/metodos_pago_usuario/{id}
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.ok().build();
    }
}
