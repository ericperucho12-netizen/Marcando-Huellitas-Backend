package com.marcandohuellitas.api.controllers;

import com.marcandohuellitas.api.models.Pedido;
import com.marcandohuellitas.api.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {
    @Autowired
    private PedidoService service;

    @GetMapping
    public List<Pedido> listarTodos(){
        return service.obtenerTodos();
    }

    @GetMapping("{id}")
    public ResponseEntity<Pedido> obtenerPorId(@PathVariable Long id){
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
