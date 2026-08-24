package com.marcandohuellitas.api.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;
import java.math.BigDecimal;

/**
 * Modelo Entidad para MetodosPagoUsuario.
 * Esta clase representa la tabla metodos_pago_usuario en la base de datos.
 */
@Entity
@Table(name = "metodos_pago_usuario")
@Data // Genera getters y setters
@NoArgsConstructor
@AllArgsConstructor
public class MetodosPagoUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "usuario_id")
    private Long usuarioId;

    private String titular;

    @Column(name = "ultimos_digitos")
    private String ultimosDigitos;

    private String marca;

    private String expiracion;

    @Column(name = "es_principal")
    private Boolean esPrincipal;

}


