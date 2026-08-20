package com.marcandohuellitas.api.controllers;

import com.marcandohuellitas.api.dto.UsuarioLoginDTO;
import com.marcandohuellitas.api.models.Usuario;
import com.marcandohuellitas.api.services.UsuarioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST de Usuarios.
 * Esta clase es la encargada de recibir las peticiones HTTP de tu Frontend (Ej: los Fetch con JSON en Javascript).
 * No contiene lógica pesada, solo actúa como puente entre el Frontend y los Servicios.
 */
@RestController // Indica que esta clase responderá con datos (generalmente en formato JSON)
@RequestMapping("/api/auth") // Todas las URLs de este archivo empezarán con http://localhost:8080/api/auth
public class UsuarioController {

    @Autowired
    private UsuarioServices usuarioServices; // Conectamos el Controlador con los Servicios

    /**
     * Endpoint para Registrar un Usuario.
     * URL: POST http://localhost:8080/api/auth/registro
     * @RequestBody Usuario usuario: Toma el JSON que manda el frontend y lo convierte en un objeto Usuario de Java.
     */
    @PostMapping("/registro")
    public ResponseEntity<?> registrarUsuario(@RequestBody Usuario usuario) {
        try {
            // Se lo mandamos al Servicio para que lo encripté y lo guarde
            Usuario nuevoUsuario = usuarioServices.registrarUsuario(usuario);
            // Si todo sale bien, respondemos con Código 200 (OK) y los datos del nuevo usuario
            return ResponseEntity.ok(nuevoUsuario);
        } catch (Exception e) {
            // Si hay un error (ej. el correo ya existe), devolvemos Código 400 (Bad Request)
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Endpoint para Iniciar Sesión.
     * URL: POST http://localhost:8080/api/auth/login
     * @RequestBody UsuarioLoginDTO loginDTO: Toma el correo y contraseña del JSON del frontend.
     */
    @PostMapping("/login")
    public ResponseEntity<?> loginUsuario(@RequestBody UsuarioLoginDTO loginDTO) {
        // Le preguntamos al Servicio si las credenciales son correctas y si no está bloqueado
        String resultado = usuarioServices.loginUsuario(loginDTO.getCorreo(), loginDTO.getPassword());
        
        // Dependiendo de lo que nos responda el servicio, devolvemos un código HTTP diferente
        if (resultado.equals("Login exitoso")) {
            return ResponseEntity.ok(resultado); // 200 OK
        } else if (resultado.contains("bloqueada")) {
            return ResponseEntity.status(403).body(resultado); // 403 Forbidden (Prohibido el paso, cuenta bloqueada)
        } else {
            return ResponseEntity.status(401).body(resultado); // 401 Unauthorized (Credenciales malas)
        }
    }
}
