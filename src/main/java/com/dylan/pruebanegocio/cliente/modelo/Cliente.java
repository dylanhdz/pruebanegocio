package com.dylan.pruebanegocio.cliente.modelo;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "clientes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String tipoidentificacion;

    @Column(nullable = false, unique = true)
    private String numeroidentificacion;

    @Column(nullable = false)
    private String nombres;

    private String correo;
    private String telefono;
}
