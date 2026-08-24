package com.marcandohuellitas.api.services;

import com.marcandohuellitas.api.models.Usuario;
import com.marcandohuellitas.api.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

/**
 * Capa de Servicios de Usuarios.
 * Aqui vive la logica de negocio. Los controladores no toman decisiones importantes,
 * solo reciben peticiones y se las pasan a los servicios.
 */
@Service
public class UsuarioServices {

    private final int MAX_INTENTOS = 5;
    private final int MINUTOS_BLOQUEO = 15;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String correoRemitente;

    // ==========================================
    // REGISTRO
    // ==========================================

    @Transactional
    public Usuario registrarUsuario(Usuario usuario) {
        if (usuarioRepository.findByCorreo(usuario.getCorreo()).isPresent()) {
            throw new RuntimeException("El correo ya esta registrado");
        }
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuario.setRol(usuario.getRol() != null ? usuario.getRol() : "USUARIO");
        usuario.setIntentosFallidos(0);
        return usuarioRepository.save(usuario);
    }

    // ==========================================
    // LOGIN NORMAL (email + password)
    // ==========================================

    @Transactional
    public Usuario loginUsuario(String correo, String password) {
        Optional<Usuario> userOpt = usuarioRepository.findByCorreo(correo);
        if (userOpt.isEmpty()) {
            throw new RuntimeException("Credenciales incorrectas");
        }

        Usuario usuario = userOpt.get();

        if (usuario.getBloqueadoHasta() != null && usuario.getBloqueadoHasta().isAfter(LocalDateTime.now())) {
            throw new RuntimeException("Cuenta bloqueada temporalmente. Intente nuevamente mas tarde.");
        }

        if (usuario.getBloqueadoHasta() != null && usuario.getBloqueadoHasta().isBefore(LocalDateTime.now())) {
            usuario.setBloqueadoHasta(null);
            usuario.setIntentosFallidos(0);
        }

        if (passwordEncoder.matches(password, usuario.getPassword())) {
            usuario.setIntentosFallidos(0);
            usuario.setBloqueadoHasta(null);
            usuario.setUltimoLogin(LocalDateTime.now());
            return usuarioRepository.save(usuario);
        } else {
            int intentos = usuario.getIntentosFallidos() == null ? 0 : usuario.getIntentosFallidos();
            intentos++;
            usuario.setIntentosFallidos(intentos);

            if (intentos >= MAX_INTENTOS) {
                usuario.setBloqueadoHasta(LocalDateTime.now().plusMinutes(MINUTOS_BLOQUEO));
                usuarioRepository.save(usuario);
                throw new RuntimeException("Cuenta bloqueada por demasiados intentos fallidos.");
            }

            usuarioRepository.save(usuario);
            throw new RuntimeException("Credenciales incorrectas. Intentos restantes: " + (MAX_INTENTOS - intentos));
        }
    }

    // ==========================================
    // LOGIN CON GOOGLE
    // ==========================================

    @Transactional
    public Usuario loginConGoogle(String idTokenString) {
        org.springframework.web.client.RestTemplate restTemplate = new org.springframework.web.client.RestTemplate();
        String url = "https://oauth2.googleapis.com/tokeninfo?id_token=" + idTokenString;
        try {
            java.util.Map<String, Object> payload = restTemplate.getForObject(url, java.util.Map.class);
            if (payload == null || !payload.containsKey("email")) {
                throw new RuntimeException("Token de Google invalido");
            }

            String email = (String) payload.get("email");
            String nombre = (String) payload.get("given_name");
            String apellido = payload.containsKey("family_name") ? (String) payload.get("family_name") : " ";

            String aud = (String) payload.get("aud");
            if (!aud.equals("797768008218-iebmh990bsun874gjp4jakamjqmsh5hn.apps.googleusercontent.com")) {
                throw new RuntimeException("Client ID de Google no coincide");
            }

            Optional<Usuario> userOpt = usuarioRepository.findByCorreo(email);
            if (userOpt.isPresent()) {
                return userOpt.get();
            } else {
                Usuario nuevoUsuario = new Usuario();
                nuevoUsuario.setCorreo(email);
                nuevoUsuario.setNombre(nombre != null ? nombre : "Usuario");
                nuevoUsuario.setApellido(apellido);
                nuevoUsuario.setPassword(passwordEncoder.encode(UUID.randomUUID().toString()));
                nuevoUsuario.setRol("USUARIO");
                nuevoUsuario.setIntentosFallidos(0);
                return usuarioRepository.save(nuevoUsuario);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al verificar con Google: " + e.getMessage());
        }
    }

    // ==========================================
    // RECUPERACION DE CONTRASENA
    // ==========================================

    @Transactional
    public void solicitarRecuperacion(String correo) {
        Optional<Usuario> userOpt = usuarioRepository.findByCorreo(correo);
        if (userOpt.isEmpty()) {
            throw new RuntimeException("No existe ninguna cuenta con ese correo");
        }

        Usuario usuario = userOpt.get();
        String token = UUID.randomUUID().toString();
        usuario.setTokenRecuperacion(token);
        usuarioRepository.save(usuario);

        SimpleMailMessage mensaje = new SimpleMailMessage();
        mensaje.setFrom(correoRemitente);
        mensaje.setTo(correo);
        mensaje.setSubject("Recuperacion de Contrasena - Marcando Huellitas");
        mensaje.setText(
            "Hola " + usuario.getNombre() + ",\n\n" +
            "Recibimos una solicitud para restablecer tu contrasena.\n\n" +
            "Tu codigo de recuperacion es:\n\n" +
            "  " + token + "\n\n" +
            "Copia y pega este codigo en la pagina de recuperacion.\n" +
            "Si no solicitaste este cambio, ignora este correo.\n\n" +
            "El equipo de Marcando Huellitas"
        );
        mailSender.send(mensaje);
    }

    @Transactional
    public void resetPassword(String token, String nuevaPassword) {
        Optional<Usuario> userOpt = usuarioRepository.findByTokenRecuperacion(token);
        if (userOpt.isEmpty()) {
            throw new RuntimeException("Token invalido o expirado");
        }

        Usuario usuario = userOpt.get();
        usuario.setPassword(passwordEncoder.encode(nuevaPassword));
        usuario.setTokenRecuperacion(null);
        usuario.setIntentosFallidos(0);
        usuario.setBloqueadoHasta(null);
        usuarioRepository.save(usuario);
    }
}