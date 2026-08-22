package com.marcandohuellitas.api.controllers;

import com.marcandohuellitas.api.models.HistoriaExito;
import com.marcandohuellitas.api.services.HistoriaExitoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/historias-exito")
public class HistoriaExitoController {

    private final HistoriaExitoService historiaExitoService;

    @Autowired
    public HistoriaExitoController(HistoriaExitoService historiaExitoService) {
        this.historiaExitoService = historiaExitoService;
    }

    @GetMapping
    public ResponseEntity<List<HistoriaExito>> obtenerTodas() {
        return ResponseEntity.ok(historiaExitoService.obtenerTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HistoriaExito> obtenerPorId(@PathVariable Long id) {
        return historiaExitoService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<HistoriaExito> crear(@RequestBody HistoriaExito historiaExito) {
        HistoriaExito nuevaHistoria = historiaExitoService.guardar(historiaExito);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaHistoria);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (historiaExitoService.obtenerPorId(id).isPresent()) {
            historiaExitoService.eliminar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}