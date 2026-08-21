package com.marcandohuellitas.api.controllers;

import com.marcandohuellitas.api.models.ProductosFavorito;
import com.marcandohuellitas.api.services.ProductosFavoritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controlador REST para ProductosFavorito.
 * Recibe las peticiones HTTP del frontend.
 */
@RestController
@RequestMapping("/api/productos_favoritos")
public class ProductosFavoritoController {

    @Autowired // Inyectamos el servicio
    private ProductosFavoritoService service;

    @GetMapping // GET /api/productos_favoritos
    public List<ProductosFavorito> listarTodos() {
        return service.obtenerTodos();
    }

    @GetMapping("/{id}") // GET /api/productos_favoritos/{id}
    public ResponseEntity<ProductosFavorito> obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping // POST /api/productos_favoritos
    public ProductosFavorito crear(@RequestBody ProductosFavorito entidad) {
        return service.guardar(entidad);
    }

    @DeleteMapping("/{id}") // DELETE /api/productos_favoritos/{id}
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.ok().build();
    }
}
