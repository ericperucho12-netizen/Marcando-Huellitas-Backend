package com.marcandohuellitas.api.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;
import java.math.BigDecimal;

/**
 * Modelo Entidad para Producto.
 * Esta clase representa la tabla productos en la base de datos.
 */
@Entity
@Table(name = "productos")
@Data // Genera getters y setters
@NoArgsConstructor
@AllArgsConstructor
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "usuario_id")
    private Long usuarioId;

    private String nombre;

    private String descripcion;

    private BigDecimal precio;

    private Integer stock;

    private String categoria;

    @Column(name = "imagen_url")
    private String imagenUrl;

    @Column(name = "creado_en", insertable = false, updatable = false)
    private LocalDateTime creadoEn;

    @Column(name = "actualizado_en", insertable = false, updatable = false)
    private LocalDateTime actualizadoEn;

}

