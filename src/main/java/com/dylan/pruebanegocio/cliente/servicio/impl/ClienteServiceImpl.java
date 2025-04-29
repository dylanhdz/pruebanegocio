package com.dylan.pruebanegocio.cliente.servicio.impl;

import com.dylan.pruebanegocio.cliente.modelo.Cliente;
import com.dylan.pruebanegocio.cliente.repositorio.ClienteRepository;
import com.dylan.pruebanegocio.cliente.servicio.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {
    private final ClienteRepository clienteRepository;

    @Override
    public Cliente crearCliente(Cliente cliente) {
        if (clienteRepository.existsByNumeroIdentificacion(cliente.getNumeroidentificacion())) {
            throw new IllegalArgumentException("El número de identificación ya existe");
        }
        return clienteRepository.save(cliente);
    }

    @Override
    
}
