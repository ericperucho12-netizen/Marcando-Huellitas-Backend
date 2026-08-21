package com.marcandohuellitas.api.controllers;

import com.marcandohuellitas.api.dto.UsuarioLoginDTO;
import com.marcandohuellitas.api.models.Usuario;
import com.marcandohuellitas.api.services.UsuarioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class UsuarioController {
    @Autowired
    private UsuarioServices usuarioServices;


    @PostMapping("/registro")
    public ResponseEntity <?> registrarUsuario(@RequestBody Usuario usuario){
        try{
            Usuario nuevoUsuario = usuarioServices.registrarUsuario(usuario);
            return ResponseEntity.ok(nuevoUsuario);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage()); //400 bad
        }
    }


    @PostMapping("/login")
    public ResponseEntity<?> loginUsuario(@RequestBody UsuarioLoginDTO loginDTO){
        String resultado = usuarioServices.loginUsuario(loginDTO.getCorreo(), loginDTO.getPassword());
        if (resultado.equals("login exitoso")){
            return ResponseEntity.ok(resultado); // 200 ok
        } else if (resultado.contains("bloqueado")) {
            return ResponseEntity.status(403).body(resultado); // 403 bloqueado

        } else {
            return ResponseEntity.status(401).body(resultado); //401 incorrecto
        }
    }


}