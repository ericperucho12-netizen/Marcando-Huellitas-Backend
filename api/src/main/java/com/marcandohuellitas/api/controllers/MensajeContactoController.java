package com.marcandohuellitas.api.controllers;

import com.marcandohuellitas.api.models.MensajeContacto;
import com.marcandohuellitas.api.services.MensajeContactoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/contacto")
@CrossOrigin(origins = "*") // Para permitir peticiones desde el frontend local
public class MensajeContactoController {

    @Autowired
    private MensajeContactoService service;

    @PostMapping
    public ResponseEntity<MensajeContacto> enviarMensaje(@RequestBody MensajeContacto mensaje) {
        MensajeContacto guardado = service.guardarMensaje(mensaje);
        return ResponseEntity.ok(guardado);
    }

    @GetMapping
    public ResponseEntity<List<MensajeContacto>> obtenerMensajes() {
        return ResponseEntity.ok(service.obtenerTodos());
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<MensajeContacto> actualizarEstado(@PathVariable Long id, @RequestBody Map<String, String> payload) {
        String estado = payload.get("estado");
        MensajeContacto actualizado = service.actualizarEstado(id, estado);
        if (actualizado != null) {
            return ResponseEntity.ok(actualizado);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMensaje(@PathVariable Long id) {
        service.eliminarMensaje(id);
        return ResponseEntity.noContent().build();
    }
}