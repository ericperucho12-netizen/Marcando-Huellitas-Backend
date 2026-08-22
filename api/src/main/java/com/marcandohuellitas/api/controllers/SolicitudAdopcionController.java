package com.marcandohuellitas.api.controllers;


import com.marcandohuellitas.api.models.Refugio;
import com.marcandohuellitas.api.models.SolicitudAdopcion;
import com.marcandohuellitas.api.services.RefugioService;
import com.marcandohuellitas.api.services.SolicitudAdopcionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/solicitudadopciones")
public class SolicitudAdopcionController {
    @Autowired
    private SolicitudAdopcionService service;

    @GetMapping
    public List<SolicitudAdopcion> listarTodos(){

        return service.obtenerTodos();
    }
    @GetMapping("/{id}")
    public ResponseEntity<SolicitudAdopcion> obtenerPorId(@PathVariable Long id){
        return service.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    public SolicitudAdopcion crear(@RequestBody SolicitudAdopcion entidad){

        return service.guardar(entidad);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        service.eliminar(id);
        return ResponseEntity.ok().build();
    }
}
