package com.marcandohuellitas.api.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

/**
 * Modelo Entidad Usuario.
 * Esta clase es el "molde" que Spring Boot utiliza para crear la tabla 'usuarios'
 * en la base de datos MySQL y mapear sus columnas a variables de Java.
 */
@Entity // Indica que esta clase es una entidad de Base de Datos
@Table(name = "usuarios") // Especifíca el nombre exacto de la tabla en MySQL
@Data // Anotación de Lombok: Genera automáticamente los Getters, Setters, toString, etc.
@NoArgsConstructor // Lombok: Crea un constructor vacío automáticamente
@AllArgsConstructor // Lombok: Crea un constructor con todos los argumentos
public class Usuario {

    @Id // Indica que este campo es la Llave Primaria (Primary Key)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Indica que es AUTO_INCREMENT
    private Long id;

    @Column(nullable = false, length = 100) // nullable = false significa NOT NULL en SQL
    private String nombre;

    @Column(nullable = false, length = 100)
    private String apellido;

    @Column(nullable = false, unique = true, length = 150) // unique = true asegura que no haya correos repetidos
    private String correo;

    @Column(nullable = false)
    private String password;

    @Column(length = 50)
    private String rol = "USUARIO"; // Por defecto, todos nacen como usuario normal

    // ==========================================
    // CAMPOS DE SEGURIDAD Y AUDITORÍA
    // ==========================================

    @Column(name = "token_recuperacion")
    private String tokenRecuperacion; // Para cuando olvidan su contraseña

    @Column(name = "intentos_fallidos")
    private Integer intentosFallidos = 0; // Contador anti Fuerza Bruta (ataques de hackers)

    @Column(name = "bloqueado_hasta")
    private LocalDateTime bloqueadoHasta; // Fecha y hora hasta la que el usuario no puede iniciar sesión

    @Column(name = "ultimo_login")
    private LocalDateTime ultimoLogin; // Guarda la fecha de la última vez que entró con éxito

    // Estos campos los maneja MySQL automáticamente con CURRENT_TIMESTAMP
    // insertable=false, updatable=false le dice a Java que no los toque al guardar
    @Column(name = "creado_en", insertable = false, updatable = false)
    private LocalDateTime creadoEn;

    @Column(name = "actualizado_en", insertable = false, updatable = false)
    private LocalDateTime actualizadoEn;
}
