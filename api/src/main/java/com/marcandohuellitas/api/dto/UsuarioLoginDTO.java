package com.marcandohuellitas.api.dto;

import lombok.Data;

/**
 * Data Transfer Object (DTO) para el Login.
 * Los DTOs son "Cajas de transporte". En lugar de pedirle al frontend que nos mande 
 * toooodos los datos de un Usuario (nombre, rol, etc.) solo para iniciar sesión,
 * creamos esta clase ligera que solo recibe lo estrictamente necesario: correo y contraseña.
 */
@Data // Lombok nos genera los Getters y Setters automáticamente por detrás
public class UsuarioLoginDTO {
    private String correo;
    private String password;
}
