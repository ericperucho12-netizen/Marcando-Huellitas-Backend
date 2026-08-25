package com.marcandohuellitas.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/favoritos")
@CrossOrigin(origins = "*")
public class FavoritoController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostMapping("/mascotas")
    public ResponseEntity<Void> agregarMascotaFavorita(@RequestBody Map<String, Long> payload) {
        Long usuarioId = payload.get("usuarioId");
        Long mascotaId = payload.get("mascotaId");
        jdbcTemplate.update("INSERT IGNORE INTO mascotas_favoritas (usuario_id, mascota_id) VALUES (?, ?)", usuarioId, mascotaId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/mascotas")
    public ResponseEntity<Void> quitarMascotaFavorita(@RequestBody Map<String, Long> payload) {
        Long usuarioId = payload.get("usuarioId");
        Long mascotaId = payload.get("mascotaId");
        jdbcTemplate.update("DELETE FROM mascotas_favoritas WHERE usuario_id = ? AND mascota_id = ?", usuarioId, mascotaId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/mascotas/{usuarioId}")
    public ResponseEntity<List<Long>> obtenerMascotasFavoritas(@PathVariable Long usuarioId) {
        List<Long> mascotasIds = jdbcTemplate.queryForList("SELECT mascota_id FROM mascotas_favoritas WHERE usuario_id = ?", Long.class, usuarioId);
        return ResponseEntity.ok(mascotasIds);
    }

    @PostMapping("/productos")
    public ResponseEntity<Void> agregarProductoFavorito(@RequestBody Map<String, Long> payload) {
        Long usuarioId = payload.get("usuarioId");
        Long productoId = payload.get("productoId");
        jdbcTemplate.update("INSERT IGNORE INTO productos_favoritos (usuario_id, producto_id) VALUES (?, ?)", usuarioId, productoId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/productos")
    public ResponseEntity<Void> quitarProductoFavorito(@RequestBody Map<String, Long> payload) {
        Long usuarioId = payload.get("usuarioId");
        Long productoId = payload.get("productoId");
        jdbcTemplate.update("DELETE FROM productos_favoritos WHERE usuario_id = ? AND producto_id = ?", usuarioId, productoId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/productos/{usuarioId}")
    public ResponseEntity<List<Long>> obtenerProductosFavoritos(@PathVariable Long usuarioId) {
        List<Long> productosIds = jdbcTemplate.queryForList("SELECT producto_id FROM productos_favoritos WHERE usuario_id = ?", Long.class, usuarioId);
        return ResponseEntity.ok(productosIds);
    }
}