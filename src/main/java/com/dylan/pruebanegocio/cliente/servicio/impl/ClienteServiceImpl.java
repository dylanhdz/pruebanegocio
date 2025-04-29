package com.dylan.pruebanegocio.cliente.servicio.impl;

import com.dylan.pruebanegocio.cliente.modelo.Cliente;
import com.dylan.pruebanegocio.cliente.repositorio.ClienteRepository;
import com.dylan.pruebanegocio.cliente.servicio.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {
    private final ClienteRepository clienteRepository;

    /*Si existe el cliente, alertar excepción, de lo contrario, guardar.*/
    @Override
    public Cliente crearCliente(Cliente cliente) {
        if (clienteRepository.existsByNumeroIdentificacion(cliente.getNumeroidentificacion())) {
            throw new IllegalArgumentException("El número de identificación ya existe");
        }
        return clienteRepository.save(cliente);
    }
    /*Buscar cliente por nombre o id*/
    @Override
    public List<Cliente> buscarClientes(String clave) {
        if (clave == null || clave.isEmpty()) {
            return clienteRepository.findAll();
        } else {
            return clienteRepository.findByNombresContainingIgnoreCaseOOrNumeroidentificacionContaining(clave, clave);
        }
    }
}
