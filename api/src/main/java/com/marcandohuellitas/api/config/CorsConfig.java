package com.marcandohuellitas.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuración CORS (Cross-Origin Resource Sharing).
 * Esto permite que nuestro frontend (HTML/JS) que corre en otro puerto (ej. Live Server en el 5500)
 * pueda hacer peticiones (GET, POST) a este Backend sin que el navegador lo bloquee por seguridad.
 */
@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**") // Aplica a todas las rutas que empiecen con /api/
                        .allowedOrigins("*") // Permite cualquier origen (frontend)
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Métodos permitidos
                        .allowedHeaders("*"); // Permite cualquier cabecera (ej. Authorization)
            }
        };
    }
}