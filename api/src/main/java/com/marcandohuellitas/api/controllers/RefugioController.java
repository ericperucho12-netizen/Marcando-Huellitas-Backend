package com.marcandohuellitas.api.controllers;

import com.marcandohuellitas.api.models.Mascota;
import com.marcandohuellitas.api.models.Refugio;
import com.marcandohuellitas.api.services.MascotaService;
import com.marcandohuellitas.api.services.RefugioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/refugios")
public class RefugioController {
    @Autowired
    private RefugioService service;

    @GetMapping
    public List<Refugio> listarTodos(){

        return service.obtenerTodos();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Refugio> obtenerPorId(@PathVariable Long id){
        return service.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    public Refugio crear(@RequestBody Refugio entidad){

        return service.guardar(entidad);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        service.eliminar(id);
        return ResponseEntity.ok().build();
    }
}
