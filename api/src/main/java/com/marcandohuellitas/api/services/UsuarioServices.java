package com.marcandohuellitas.api.services;

import com.marcandohuellitas.api.models.Usuario;
import com.marcandohuellitas.api.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Capa de Servicios de Usuarios.
 * Aquí es donde vive la "Lógica de Negocio". Los controladores no deben tomar decisiones importantes,
 * solo reciben peticiones y se las pasan a los servicios. Aquí es donde encriptamos contraseñas,
 * calculamos tiempos y nos defendemos de ataques.
 */
@Service
public class UsuarioServices {

    // Constantes para nuestra política de seguridad anti Fuerza Bruta
    private final int MAX_INTENTOS = 5;
    private final int MINUTOS_BLOQUEO = 15;

    @Autowired
    private UsuarioRepository usuarioRepository; // Conexión a la Base de Datos

    @Autowired
    private PasswordEncoder passwordEncoder; // Herramienta de encriptación (BCrypt) que configuramos en SecurityConfig

    /**
     * Lógica para Registrar un nuevo usuario.
     */
    @Transactional
    public Usuario registrarUsuario(Usuario usuario) {
        // 1. Verificar si el correo ya existe en la base de datos
        if (usuarioRepository.findByCorreo(usuario.getCorreo()).isPresent()) {
            throw new RuntimeException("El correo ya está registrado");
        }
        
        // 2. Encriptar la contraseña ANTES de guardarla (Nunca guardar texto plano)
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        
        // 3. Establecer valores por defecto de seguridad
        usuario.setRol(usuario.getRol() != null ? usuario.getRol() : "USUARIO");
        usuario.setIntentosFallidos(0);
        
        // 4. Guardar en la base de datos
        return usuarioRepository.save(usuario);
    }

    /**
     * Lógica para Iniciar Sesión (Login) y defender contra Fuerza Bruta.
     */
    @Transactional
    public String loginUsuario(String correo, String password) {
        // 1. Buscar al usuario por su correo
        Optional<Usuario> userOpt = usuarioRepository.findByCorreo(correo);
        
        // Si no existe, no damos pistas de que el correo no existe por seguridad. Solo decimos "Credenciales incorrectas"
        if (userOpt.isEmpty()) {
            return "Credenciales incorrectas";
        }
        
        Usuario usuario = userOpt.get();
        
        // 2. Verificar si la cuenta está actualmente bloqueada (castigada)
        if (usuario.getBloqueadoHasta() != null && usuario.getBloqueadoHasta().isAfter(LocalDateTime.now())) {
            return "Cuenta bloqueada temporalmente. Intente nuevamente más tarde.";
        }
        
        // Si estaba bloqueada pero ya pasó el tiempo de castigo (15 minutos), la desbloqueamos
        if (usuario.getBloqueadoHasta() != null && usuario.getBloqueadoHasta().isBefore(LocalDateTime.now())) {
            usuario.setBloqueadoHasta(null);
            usuario.setIntentosFallidos(0);
        }
        
        // 3. Verificar si la contraseña coincide usando el verificador de BCrypt
        if (passwordEncoder.matches(password, usuario.getPassword())) {
            // ÉXITO: El usuario puso bien su contraseña. 
            // Reseteamos sus intentos a 0 y actualizamos su última fecha de conexión.
            usuario.setIntentosFallidos(0);
            usuario.setBloqueadoHasta(null);
            usuario.setUltimoLogin(LocalDateTime.now());
            usuarioRepository.save(usuario);
            return "Login exitoso"; // Nota: Aquí más adelante devolveremos el Token JWT
        } else {
            // FALLO: El usuario se equivocó de contraseña.
            
            // Contamos un intento fallido más
            int intentos = usuario.getIntentosFallidos() == null ? 0 : usuario.getIntentosFallidos();
            intentos++;
            usuario.setIntentosFallidos(intentos);
            
            // Si ya se equivocó 5 veces, lo bloqueamos y guardamos la fecha de desbloqueo
            if (intentos >= MAX_INTENTOS) {
                usuario.setBloqueadoHasta(LocalDateTime.now().plusMinutes(MINUTOS_BLOQUEO));
                usuarioRepository.save(usuario);
                return "Cuenta bloqueada por demasiados intentos fallidos.";
            }
            
            // Si aún le quedan intentos, solo guardamos el nuevo número de intentos y le advertimos
            usuarioRepository.save(usuario);
            return "Credenciales incorrectas. Intentos restantes: " + (MAX_INTENTOS - intentos);
        }
    }
}
