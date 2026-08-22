package com.marcandohuellitas.api.controllers;

import com.marcandohuellitas.api.dto.UsuarioLoginDTO;
import com.marcandohuellitas.api.models.Usuario;
import com.marcandohuellitas.api.services.UsuarioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST de Usuarios.
 * Esta clase es la encargada de recibir las peticiones HTTP del Frontend.
 * No contiene logica pesada, solo actua como puente entre el Frontend y los Servicios.
 */
@RestController
@RequestMapping("/api/auth")
public class UsuarioController {

    @Autowired
    private UsuarioServices usuarioServices;

    /**
     * Endpoint para Registrar un Usuario.
     * URL: POST http://localhost:8080/api/auth/registro
     */
    @PostMapping("/registro")
    public ResponseEntity<?> registrarUsuario(@RequestBody Usuario usuario) {
        try {
            Usuario nuevoUsuario = usuarioServices.registrarUsuario(usuario);
            return ResponseEntity.ok(nuevoUsuario);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Endpoint para Iniciar Sesion (email/password).
     * URL: POST http://localhost:8080/api/auth/login
     */
    @PostMapping("/login")
    public ResponseEntity<?> loginUsuario(@RequestBody UsuarioLoginDTO loginDTO) {
        try {
            Usuario usuario = usuarioServices.loginUsuario(loginDTO.getCorreo(), loginDTO.getPassword());
            return ResponseEntity.ok(usuario);
        } catch (RuntimeException e) {
            String errorMsg = e.getMessage();
            if (errorMsg.contains("bloqueada")) {
                return ResponseEntity.status(403).body(errorMsg);
            }
            return ResponseEntity.status(401).body(errorMsg);
        }
    }

    /**
     * Endpoint para Iniciar Sesion con Google.
     * URL: POST http://localhost:8080/api/auth/google
     */
    @PostMapping("/google")
    public ResponseEntity<?> loginGoogle(@RequestBody java.util.Map<String, String> body) {
        try {
            String token = body.get("token");
            Usuario usuario = usuarioServices.loginConGoogle(token);
            return ResponseEntity.ok(usuario);
        } catch (RuntimeException e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }

    /**
     * Endpoint para solicitar recuperacion de contrasena.
     * URL: POST http://localhost:8080/api/auth/recuperar
     * Body: { "correo": "usuario@email.com" }
     */
    @PostMapping("/recuperar")
    public ResponseEntity<?> solicitarRecuperacion(@RequestBody java.util.Map<String, String> body) {
        try {
            String correo = body.get("correo");
            usuarioServices.solicitarRecuperacion(correo);
            return ResponseEntity.ok("Correo de recuperacion enviado correctamente");
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al enviar el correo: " + e.getMessage());
        }
    }

    /**
     * Endpoint para restablecer la contrasena con el token recibido por correo.
     * URL: POST http://localhost:8080/api/auth/reset-password
     * Body: { "token": "uuid-token", "nuevaPassword": "NuevaPass123" }
     */
    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody java.util.Map<String, String> body) {
        try {
            String token = body.get("token");
            String nuevaPassword = body.get("nuevaPassword");
            usuarioServices.resetPassword(token, nuevaPassword);
            return ResponseEntity.ok("Contrasena actualizada correctamente");
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}