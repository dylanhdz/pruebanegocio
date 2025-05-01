package com.dylan.pruebanegocio.cliente;

import com.dylan.pruebanegocio.cliente.modelo.Cliente;
import com.dylan.pruebanegocio.cliente.repositorio.ClienteRepository;
import com.dylan.pruebanegocio.cliente.servicio.ClienteService;
import com.dylan.pruebanegocio.cliente.servicio.impl.ClienteServiceImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class JUnitTDDTest {
    // Fase Roja: Falla el test, porque el prototipo no existe
    @Test
    public void creoClienteValido_clienteSeGuarda() {
        // Preparar
        ClienteRepositoryPrototipo clienteRepository = new ClienteRepositoryPrototipo();
        Cliente clienteSolicitado = Cliente.builder()
                .nombres("Dylan Hernández")
                .numeroIdentificacion("1234567890")
                .build();
        // Actuar
        Cliente resultado = clienteRepository.crearCliente(clienteSolicitado);
        // Asegurar
        assertNotNull(resultado.getId());
        assertEquals("Dylan Hernández", resultado.getNombres());
    }

}
