package com.marcandohuellitas.api.controllers;

import com.marcandohuellitas.api.models.Pedido;
import com.marcandohuellitas.api.services.PedidoService;
import com.marcandohuellitas.api.models.SolicitudApoyo;
import com.marcandohuellitas.api.services.SolicitudApoyoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/solicitudApoyo")
public class SolicitudApoyoController {
    @Autowired
    private SolicitudApoyoService service;

    @GetMapping
    public List<SolicitudApoyo> listarTodos(){
        return service.obtenerTodos();
    }

    @GetMapping("{id}")
    public ResponseEntity<SolicitudApoyo> obtenerPorId(@PathVariable Long id){
        return service.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        service.eliminar(id);
        return ResponseEntity.ok().build();
    }
}
