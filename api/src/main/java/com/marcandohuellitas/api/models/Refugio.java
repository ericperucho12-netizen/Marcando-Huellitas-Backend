package com.marcandohuellitas.api.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;
import java.math.BigDecimal;

/**
 * Modelo Entidad para Refugio.
 * Esta clase representa la tabla refugios en la base de datos.
 */
@Entity
@Table(name = "refugios")
@Data // Genera getters y setters
@NoArgsConstructor
@AllArgsConstructor
public class Refugio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "usuario_id")
    private Long usuarioId;

    private String nombre;

    private String responsable;

    private String correo;

    private String telefono;

    private String direccion;

    @Column(name = "estado_entidad")
    private String estadoEntidad;

    @Column(name = "tipo_organizacion")
    private String tipoOrganizacion;

    private String descripcion;

    @Column(name = "sitio_web")
    private String sitioWeb;

    private String instagram;

    private String facebook;

    @Column(name = "imagen_url")
    private String imagenUrl;

    @Column(name = "video_url")
    private String videoUrl;

    private String estatus;

    @Column(name = "creado_en", insertable = false, updatable = false)
    private LocalDateTime creadoEn;

}

