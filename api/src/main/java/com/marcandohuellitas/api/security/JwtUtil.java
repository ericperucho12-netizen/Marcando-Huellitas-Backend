package com.marcandohuellitas.api.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Utilidad principal para el manejo de JWT (JSON Web Tokens).
 * Esta clase se encarga de crear nuevos tokens cuando un usuario inicia sesion,
 * y de leer/validar los tokens cuando un usuario intenta acceder a una ruta protegida.
 */
@Component
public class JwtUtil {

    // Clave secreta usada para firmar los tokens. Es muy importante que esta clave sea larga
    // y segura para que nadie mas pueda falsificar nuestros tokens.
    private final String SECRET_KEY = "MarcandoHuellitasClaveSecretaMuySeguraParaTokensJWT2026!";

    /**
     * Genera la llave criptografica a partir del texto de la clave secreta.
     */
    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    /**
     * Extrae el nombre de usuario (en nuestro caso, el correo electronico) de un token dado.
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Extrae la fecha en la que el token va a expirar.
     */
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * Metodo generico para extraer cualquier "claim" (dato) de un token.
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Desencripta y lee todo el contenido (payload) del token verificando la firma.
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey()) // Verifica que el token no haya sido alterado
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    /**
     * Verifica si un token ya alcanzo su fecha de vencimiento.
     */
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * Crea un token nuevo a partir de los datos de un usuario (UserDetails).
     */
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        // Puedes agregar más datos al token usando claims.put("clave", "valor") si lo necesitas en el futuro
        return createToken(claims, userDetails.getUsername());
    }

    /**
     * Construye fisicamente el string del token.
     */
    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .claims(claims)
                .subject(subject) // El sujeto (subject) es a quien pertenece el token (el correo)
                .issuedAt(new Date(System.currentTimeMillis())) // Fecha de creacion
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 horas de duracion
                .signWith(getSigningKey()) // Firmamos con la clave secreta
                .compact();
    }

    /**
     * Verifica que un token pertenezca al usuario correcto y que no este expirado.
     */
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}