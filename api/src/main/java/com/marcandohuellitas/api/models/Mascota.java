package com.marcandohuellitas.api.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

/**
 * Modelo Entidad para Mascota.
 * Esta clase representa la tabla mascotas en la base de datos.
 */
@Entity
@Table(name = "mascotas")
@Data // Genera getters y setters
@NoArgsConstructor
@AllArgsConstructor
public class Mascota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String especie;

    private String raza;

    private String sexo;

    private String tamano;

    private String edad;

    private String descripcion;

    private String estado;

    @Column(name = "imagen_url")
    private String imagenUrl;

    private String caracteristicas;

    @Column(name = "refugio_id")
    private Long refugioId;

    @Column(name = "creado_en", insertable = false, updatable = false)
    private LocalDateTime creadoEn;

    @Column(name = "actualizado_en", insertable = false, updatable = false)
    private LocalDateTime actualizadoEn;

}
