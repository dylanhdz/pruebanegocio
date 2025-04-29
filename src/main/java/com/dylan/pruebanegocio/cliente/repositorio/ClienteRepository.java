package com.dylan.pruebanegocio.cliente.repositorio;

import com.dylan.pruebanegocio.cliente.modelo.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    boolean existsByNumeroIdentificacion(String numeroIdentificacion);
}
