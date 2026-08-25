package com.marcandohuellitas.api.controllers;

import com.marcandohuellitas.api.models.Pedido;
import com.marcandohuellitas.api.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controlador REST para Pedido.
 * Recibe las peticiones HTTP del frontend.
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired // Inyectamos el servicio
    private PedidoService service;

    @GetMapping // GET /api/pedidos
    public List<Pedido> listarTodos() {
        return service.obtenerTodos();
    }

    @GetMapping("/{id}") // GET /api/pedidos/{id}
    public ResponseEntity<Pedido> obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping // POST /api/pedidos
    public Pedido crear(@RequestBody Pedido entidad) {
        return service.guardar(entidad);
    }

    @DeleteMapping("/{id}") // DELETE /api/pedidos/{id}
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.ok().build();
    }
}
