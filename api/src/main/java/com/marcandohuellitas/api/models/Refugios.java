package com.marcandohuellitas.api.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Refugios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String nombre;

    @Column(nullable = false, length = 150)
    private String responsable;

    @Column(nullable = false, length = 150)
    private String correo;

    @Column(nullable = false, length = 20)
    private String telefono;

    @Column(nullable = false, length = 255)
    private String direccion;

    @Column(nullable = false, length = 100)
    private String estado_entidad;

    @Column(nullable = false, length = 100)
    private String tipo_organizacion;

    @Column(length = 255)
    private String sitio_web;

    @Column(length = 255)
    private String instagram;

    @Column(length = 255)
    private String facebook;

    @Column(length = 255)
    private String imagen_url;

    @Column(length = 255)
    private String video_url;

    @Column(length = 50)
    private String estatus = "PENDIENTE";
}
