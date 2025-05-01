package com.dylan.pruebanegocio.cliente.servicio.impl;

import com.dylan.pruebanegocio.cliente.modelo.Cliente;
import com.dylan.pruebanegocio.cliente.modelo.Direccion;
import com.dylan.pruebanegocio.cliente.repositorio.ClienteRepository;
import com.dylan.pruebanegocio.cliente.servicio.ClienteService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {
    private final ClienteRepository clienteRepository;

    /*Si existe el cliente, alertar excepción, de lo contrario, guardar.*/
    @Override
    public Cliente crearCliente(Cliente cliente) {
        if (clienteRepository.existsByNumeroIdentificacion(cliente.getNumeroIdentificacion())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Identificación ya existe: " + cliente.getNumeroIdentificacion()
            );
        }
        return clienteRepository.save(cliente);
    }
    /*Buscar cliente por nombre o id*/
    @Override
    public List<Cliente> buscarClientes(String clave) {
        if (clave == null || clave.isEmpty()) {
            return clienteRepository.findAll();
        } else {
            return clienteRepository.findByNombresContainingIgnoreCaseOrNumeroIdentificacionContaining(clave, clave);
        }
    }

    @Override
    @Transactional
    public Direccion agregarDireccion(Long clienteId, Direccion direccion) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.CONFLICT,
                "Cliente no encontrado."
        ));

        // Validar solo una dirección matriz
        if (direccion.isEsMatriz() && cliente.getDireccionMatriz() != null) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "El cliente ya tiene una dirección matriz."
            );
        }

        direccion.setCliente(cliente);

        if (direccion.isEsMatriz()) {
            cliente.setDireccionMatriz(direccion);
        } else {
            cliente.getDireccionesAdicionales().add(direccion);
        }

        clienteRepository.save(cliente); // Actualiza en cascada
        return direccion;
    }

    @Override
    public List<Direccion> listarDirecciones(Long clienteId) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.CONFLICT,
                        "Cliente no encontrado."
                ));

        List<Direccion> direcciones = new ArrayList<>();

        direcciones.addAll(cliente.getDireccionesAdicionales());

        return direcciones;
    }

    @Override
    @Transactional
    public Cliente editarCliente(Long clienteId, Cliente cliente) {
        Cliente clienteExistente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.CONFLICT,
                        "Cliente no encontrado."
                ));
        // Validar si el número de identificación ya existe
        if (cliente.getNumeroIdentificacion() != null &&
            clienteRepository.existsByNumeroIdentificacion(cliente.getNumeroIdentificacion()) &&
            !clienteExistente.getNumeroIdentificacion().equals(cliente.getNumeroIdentificacion())) {
                throw new ResponseStatusException(
                        HttpStatus.CONFLICT,
                        "Esa identificación ya existe: " + cliente.getNumeroIdentificacion()
                );
        }
        if (cliente.getTipoidentificacion() != null) {
            clienteExistente.setTipoidentificacion(cliente.getTipoidentificacion());
        }
        if (cliente.getNumeroIdentificacion() != null) {
            clienteExistente.setNumeroIdentificacion(cliente.getNumeroIdentificacion());
        }
        if (cliente.getNombres() != null) {
            clienteExistente.setNombres(cliente.getNombres());
        }
        if (cliente.getCorreo() != null) {
            clienteExistente.setCorreo(cliente.getCorreo());
        }
        if (cliente.getTelefono() != null) {
            clienteExistente.setTelefono(cliente.getTelefono());
        }
        // Guardar el cliente actualizado
        return clienteRepository.save(clienteExistente);
    }

    @Override
    @Transactional
    public void eliminarCliente(Long clienteId) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.CONFLICT,
                        "Cliente no encontrado."
                ));

        // Eliminar las direcciones asociadas
        cliente.getDireccionesAdicionales().clear();
        cliente.setDireccionMatriz(null);
        // Eliminar el cliente
        clienteRepository.delete(cliente);
    }
}
