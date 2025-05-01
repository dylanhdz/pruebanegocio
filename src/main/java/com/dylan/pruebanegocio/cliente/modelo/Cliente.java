package com.dylan.pruebanegocio.cliente.modelo;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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
    private String numeroIdentificacion;

    @Column(nullable = false)
    private String nombres;

    private String correo;
    private String telefono;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "direccion_matriz_id")
    private Direccion direccionMatriz;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Direccion> direccionesAdicionales = new ArrayList<>();
}
