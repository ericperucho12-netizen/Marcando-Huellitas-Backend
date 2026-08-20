package com.marcandohuellitas.api.controllers;

import com.marcandohuellitas.api.dto.UsuarioLoginDTO;
import com.marcandohuellitas.api.models.Usuario;
import com.marcandohuellitas.api.services.UsuarioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class UsuarioController {

    @Autowired
    private UsuarioServices usuarioServices;

    @PostMapping("/registro")
    public ResponseEntity<?> registrarUsuario(@RequestBody Usuario usuario) {
        try {
            Usuario nuevoUsuario = usuarioServices.registrarUsuario(usuario);
            return ResponseEntity.ok(nuevoUsuario);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUsuario(@RequestBody UsuarioLoginDTO loginDTO) {
        String resultado = usuarioServices.loginUsuario(loginDTO.getCorreo(), loginDTO.getPassword());
        
        if (resultado.equals("Login exitoso")) {
            return ResponseEntity.ok(resultado);
        } else if (resultado.contains("bloqueada")) {
            return ResponseEntity.status(403).body(resultado); // 403 Forbidden para cuentas bloqueadas
        } else {
            return ResponseEntity.status(401).body(resultado); // 401 Unauthorized para credenciales incorrectas
        }
    }
}
