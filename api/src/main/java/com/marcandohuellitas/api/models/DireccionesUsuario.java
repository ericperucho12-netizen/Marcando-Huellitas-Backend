package com.marcandohuellitas.api.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;
import java.math.BigDecimal;

/**
 * Modelo Entidad para DireccionesUsuario.
 * Esta clase representa la tabla direcciones_usuario en la base de datos.
 */
@Entity
@Table(name = "direcciones_usuario")
@Data // Genera getters y setters
@NoArgsConstructor
@AllArgsConstructor
public class DireccionesUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "usuario_id")
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


