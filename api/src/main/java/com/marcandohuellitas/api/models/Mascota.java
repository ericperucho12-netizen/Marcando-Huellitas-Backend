package com.marcandohuellitas.api.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "mascotas")
@Data
@NoArgsConstructor@AllArgsConstructor
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
    private Long refugioid;
    @Column(name = "creado_en", insertable = false, updatable = false)
    private LocalDateTime creadoEn;
    @Column(name = "actualizado_en", insertable = false, updatable = false)
    private  LocalDateTime actualizadoEn;

}
