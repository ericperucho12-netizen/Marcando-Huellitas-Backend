package com.marcandohuellitas.api.security;

import com.marcandohuellitas.api.models.Usuario;
import com.marcandohuellitas.api.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * Servicio personalizado que le enseña a Spring Security como buscar a los usuarios
 * en nuestra propia base de datos usando el UsuarioRepository.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Este metodo es llamado automaticamente por Spring Security cada vez que necesita
     * comprobar la identidad de un usuario (por ejemplo, cuando lee un token JWT o se hace login).
     *
     * @param email El correo del usuario (Spring Security le llama "username", pero nosotros usamos email)
     * @return UserDetails objeto estandar de Spring Security con credenciales y roles
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // 1. Buscamos al usuario en nuestra base de datos por su correo
        Usuario usuario = usuarioRepository.findByCorreo(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con el correo: " + email));

        // 2. Extraemos su rol (ADMIN o USUARIO). 
        // Spring Security maneja los roles agregando "ROLE_" al inicio del nombre del rol, 
        // por lo que si en la base de datos dice "ADMIN", aqui lo convertimos a "ROLE_ADMIN".
        String role = usuario.getRol() != null ? usuario.getRol().toUpperCase() : "USUARIO";
        if (!role.startsWith("ROLE_")) {
            role = "ROLE_" + role;
        }

        // 3. Devolvemos el usuario empaquetado en el formato que Spring Security entiende
        return new org.springframework.security.core.userdetails.User(
                usuario.getCorreo(),
                usuario.getPassword(), // Contraseña (debe estar encriptada en la BD)
                Collections.singletonList(new SimpleGrantedAuthority(role)) // Le pasamos su rol
        );
    }
}