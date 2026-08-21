package com.marcandohuellitas.api.controllers;

import com.marcandohuellitas.api.models.DetallePedido;
import com.marcandohuellitas.api.services.DetallePedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controlador REST para DetallePedido.
 * Recibe las peticiones HTTP del frontend.
 */
@RestController
@RequestMapping("/api/detalles_pedido")
public class DetallePedidoController {

    @Autowired // Inyectamos el servicio
    private DetallePedidoService service;

    @GetMapping // GET /api/detalles_pedido
    public List<DetallePedido> listarTodos() {
        return service.obtenerTodos();
    }

    @GetMapping("/{id}") // GET /api/detalles_pedido/{id}
    public ResponseEntity<DetallePedido> obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping // POST /api/detalles_pedido
    public DetallePedido crear(@RequestBody DetallePedido entidad) {
        return service.guardar(entidad);
    }

    @DeleteMapping("/{id}") // DELETE /api/detalles_pedido/{id}
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.ok().build();
    }
}
