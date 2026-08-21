package com.marcandohuellitas.api.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "producto")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private int stock;
    private String categoria;
    @Column(name = "imagen_url")
    private String imagenUrl;
    @Column(name = "creado_en", insertable = false, updatable = false)
    private LocalDateTime creadoEn;
    @Column(name = "actualizado_en", insertable = false, updatable = false)
    private  LocalDateTime actualizadoEn;

}
