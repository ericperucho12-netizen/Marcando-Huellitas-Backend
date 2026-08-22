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
public class DireccionesUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name ="usuario_id")
    private Long usuarioId;

    @Column(name = "calle_numero")
    private String calleNumero;

    private String colonia;

    @Column(name = "codigo_postal")
    private String codigoPostal;

    @Column(name = "ciudad_estado")
    private String ciudadEstado;

    private String referencias;

    @Column(name = "es_principal")
    private Boolean esPrincipal;

}
