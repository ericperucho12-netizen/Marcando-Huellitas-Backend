package com.marcandohuellitas.api.controllers;

import com.marcandohuellitas.api.models.DireccionesUsuario;
import com.marcandohuellitas.api.services.DireccionesUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/direcciones")
public class DireccionesUsuarioController {
    @Autowired
    private DireccionesUsuarioService service;

    @GetMapping
    public List<DireccionesUsuario> listarTodos() {
        return service.obtenerTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DireccionesUsuario> obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public DireccionesUsuario crear(@RequestBody DireccionesUsuario entidad) {
        return service.guardar(entidad);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        service.eliminar(id);
        return ResponseEntity.ok().build();
    }


}
