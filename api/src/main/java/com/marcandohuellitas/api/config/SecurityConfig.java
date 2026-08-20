package com.marcandohuellitas.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Clase de configuración principal para Spring Security.
 * Aquí definimos cómo se protegerá nuestra aplicación (quién puede entrar a qué rutas)
 * y qué herramientas usaremos para encriptar datos.
 */
@Configuration // Le dice a Spring que esta clase contiene configuraciones que debe cargar al iniciar
@EnableWebSecurity // Activa la seguridad web en nuestro proyecto
public class SecurityConfig {

    /**
     * @Bean le indica a Spring que guarde este objeto (el encriptador) en su memoria (Contexto)
     * para que podamos inyectarlo (@Autowired) y usarlo en otros archivos (como en UsuarioServices).
     * 
     * BCryptPasswordEncoder es el algoritmo estándar y más seguro para encriptar contraseñas.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Este método configura la "Cadena de Filtros de Seguridad".
     * Es como el cadenero del antro: revisa cada petición HTTP que llega al servidor.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // Deshabilitamos CSRF (Cross-Site Request Forgery) porque nuestra API será REST (no usa sesiones ni cookies tradicionales, usaremos Tokens después)
            .csrf(csrf -> csrf.disable()) 
            
            // Configuramos los permisos de las rutas (Endpoints)
            .authorizeHttpRequests(auth -> auth
                // Permitimos que CUALQUIERA (sin estar logueado) acceda a las rutas que empiezan con /api/auth/ (login y registro)
                .requestMatchers("/api/auth/**").permitAll() 
                
                // Temporalmente, permitimos acceso a TODAS las demás rutas. 
                // TODO: Más adelante, cambiaremos esto a .authenticated() para obligar a usar un Token JWT
                .anyRequest().permitAll() 
            );
        
        return http.build();
    }
}
