package com.marcandohuellitas.api.controllers;

import com.marcandohuellitas.api.models.Producto;
import com.marcandohuellitas.api.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controlador REST para Producto.
 * Recibe las peticiones HTTP del frontend.
 */
@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired // Inyectamos el servicio
    private ProductoService service;

    @GetMapping // GET /api/productos
    public List<Producto> listarTodos() {
        return service.obtenerTodos();
    }

    @GetMapping("/{id}") // GET /api/productos/{id}
    public ResponseEntity<Producto> obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping // POST /api/productos
    public Producto crear(@RequestBody Producto entidad) {
        return service.guardar(entidad);
    }

    @PutMapping("/{id}") // PUT /api/productos/{id}
    public ResponseEntity<Producto> actualizar(@PathVariable Long id, @RequestBody Producto entidad) {
        return service.obtenerPorId(id)
                .map(productoExistente -> {
                    entidad.setId(id);
                    return ResponseEntity.ok(service.guardar(entidad));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}") // DELETE /api/productos/{id}
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.ok().build();
    }
}
