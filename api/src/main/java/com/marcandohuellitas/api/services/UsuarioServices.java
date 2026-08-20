package com.marcandohuellitas.api.services;

import com.marcandohuellitas.api.models.Usuario;
import com.marcandohuellitas.api.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UsuarioServices {

    private final int MAX_INTENTOS = 5;
    private final int MINUTOS_BLOQUEO = 15;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public Usuario registrarUsuario(Usuario usuario) {
        if (usuarioRepository.findByCorreo(usuario.getCorreo()).isPresent()) {
            throw new RuntimeException("El correo ya está registrado");
        }
        
        // Encriptar la contraseña usando BCrypt
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuario.setRol(usuario.getRol() != null ? usuario.getRol() : "USUARIO");
        usuario.setIntentosFallidos(0);
        
        return usuarioRepository.save(usuario);
    }

    @Transactional
    public String loginUsuario(String correo, String password) {
        Optional<Usuario> userOpt = usuarioRepository.findByCorreo(correo);
        
        if (userOpt.isEmpty()) {
            return "Credenciales incorrectas";
        }
        
        Usuario usuario = userOpt.get();
        
        // Verificar si la cuenta está bloqueada
        if (usuario.getBloqueadoHasta() != null && usuario.getBloqueadoHasta().isAfter(LocalDateTime.now())) {
            return "Cuenta bloqueada temporalmente. Intente nuevamente más tarde.";
        }
        
        // Si estaba bloqueada pero el tiempo ya pasó, desbloquear
        if (usuario.getBloqueadoHasta() != null && usuario.getBloqueadoHasta().isBefore(LocalDateTime.now())) {
            usuario.setBloqueadoHasta(null);
            usuario.setIntentosFallidos(0);
        }
        
        // Verificar contraseña
        if (passwordEncoder.matches(password, usuario.getPassword())) {
            // Éxito: Resetear intentos y actualizar último login
            usuario.setIntentosFallidos(0);
            usuario.setBloqueadoHasta(null);
            usuario.setUltimoLogin(LocalDateTime.now());
            usuarioRepository.save(usuario);
            return "Login exitoso"; // Aquí más adelante devolveremos el Token JWT
        } else {
            // Fallo de contraseña: Incrementar intentos
            int intentos = usuario.getIntentosFallidos() == null ? 0 : usuario.getIntentosFallidos();
            intentos++;
            usuario.setIntentosFallidos(intentos);
            
            if (intentos >= MAX_INTENTOS) {
                usuario.setBloqueadoHasta(LocalDateTime.now().plusMinutes(MINUTOS_BLOQUEO));
                usuarioRepository.save(usuario);
                return "Cuenta bloqueada por demasiados intentos fallidos.";
            }
            
            usuarioRepository.save(usuario);
            return "Credenciales incorrectas. Intentos restantes: " + (MAX_INTENTOS - intentos);
        }
    }
}
