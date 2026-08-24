package com.marcandohuellitas.api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Filtro de Autenticacion JWT.
 * Se encarga de interceptar CADA peticion (request) HTTP que entra a nuestra API
 * para revisar si el usuario envio un token JWT valido.
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    /**
     * Este metodo es el corazon del filtro. Se ejecuta en cada peticion.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // 1. Buscamos en las cabeceras de la peticion (Headers) si existe un "Authorization"
        final String authHeader = request.getHeader("Authorization");
        String email = null;
        String jwt = null;

        // 2. Si existe un header Authorization y empieza con "Bearer ", extraemos el Token
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            jwt = authHeader.substring(7); // Extraemos el token quitando los primeros 7 caracteres ("Bearer ")
            try {
                // Leemos el token para sacar a quien pertenece (el correo del usuario)
                email = jwtUtil.extractUsername(jwt);
            } catch (Exception e) {
                logger.error("Token invalido", e);
            }
        }

        // 3. Si encontramos un correo en el token, y el usuario AUN no esta autenticado en el contexto de Spring...
        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            
            // Cargamos el usuario desde nuestra BD para verificar que siga existiendo y obtener sus roles actuales
            UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(email);

            // Verificamos matematicamente (la firma criptografica) que el token de verdad pertenece a este usuario
            if (jwtUtil.validateToken(jwt, userDetails)) {
                
                // Si todo es valido, creamos un "pase" de autenticacion oficial para Spring Security
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // 4. Guardamos este pase oficial en el Contexto de Seguridad. 
                // A partir de esta linea, Spring sabe que el usuario esta autenticado y le permite pasar a las rutas protegidas.
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        
        // 5. Dejamos que la peticion siga su camino (hacia el controlador u otros filtros)
        filterChain.doFilter(request, response);
    }
}