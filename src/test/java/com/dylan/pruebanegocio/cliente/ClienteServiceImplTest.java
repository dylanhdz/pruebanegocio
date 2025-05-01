package com.dylan.pruebanegocio.cliente;

import com.dylan.pruebanegocio.cliente.modelo.Cliente;
import com.dylan.pruebanegocio.cliente.servicio.impl.ClienteServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import com.dylan.pruebanegocio.cliente.repositorio.ClienteRepository;
@ExtendWith(MockitoExtension.class)
public class ClienteServiceImplTest {
    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteServiceImpl clienteService;

    @Test
    void crearCliente_ClienteValido_CreaCliente() {
        // Arregla
        Cliente clienteSolicitado = Cliente.builder()
                .nombres("Dylan Hernández")
                .numeroIdentificacion("1234567890")
                .build();
        // Actúa
        clienteService.crearCliente(clienteSolicitado);
        // Asegura
        // Verificar que el cliente fue guardado en el repositorio
        Mockito.verify(clienteRepository).save(clienteSolicitado);
    }
}
