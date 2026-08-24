package com.marcandohuellitas.api.controllers;

import com.marcandohuellitas.api.dto.AuthResponseDTO;
import com.marcandohuellitas.api.dto.UsuarioLoginDTO;
import com.marcandohuellitas.api.models.Usuario;
import com.marcandohuellitas.api.security.JwtUtil;
import com.marcandohuellitas.api.services.UsuarioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class UsuarioController {

    @Autowired
    private UsuarioServices usuarioServices;

    @Autowired
    private JwtUtil jwtUtil;

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
        try {
            Usuario usuario = usuarioServices.loginUsuario(loginDTO.getCorreo(), loginDTO.getPassword());
            
            UserDetails userDetails = new User(
                usuario.getCorreo(),
                usuario.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + usuario.getRol()))
            );
            
            String token = jwtUtil.generateToken(userDetails);
            return ResponseEntity.ok(new AuthResponseDTO(token, usuario));
        } catch (RuntimeException e) {
            String errorMsg = e.getMessage();
            if (errorMsg.contains("bloqueada")) {
                return ResponseEntity.status(403).body(errorMsg);
            }
            return ResponseEntity.status(401).body(errorMsg);
        }
    }

    @PostMapping("/google")
    public ResponseEntity<?> loginGoogle(@RequestBody java.util.Map<String, String> body) {
        try {
            String tokenGoogle = body.get("token");
            Usuario usuario = usuarioServices.loginConGoogle(tokenGoogle);
            
            UserDetails userDetails = new User(
                usuario.getCorreo(),
                usuario.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + usuario.getRol()))
            );
            
            String jwt = jwtUtil.generateToken(userDetails);
            return ResponseEntity.ok(new AuthResponseDTO(jwt, usuario));
        } catch (RuntimeException e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }

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