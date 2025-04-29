package com.dylan.pruebanegocio.cliente.repositorio;

import com.dylan.pruebanegocio.cliente.modelo.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    boolean existsByNumeroIdentificacion(String numeroIdentificacion);
    /* Composición para hallar o nombres o número de identificación */
    List<Cliente> findByNombresContainingIgnoreCaseOrNumeroIdentificacionContaining(String nombres, String numeroIdentificacion);
}
