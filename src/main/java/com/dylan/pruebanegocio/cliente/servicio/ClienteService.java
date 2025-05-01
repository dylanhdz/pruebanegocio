package com.dylan.pruebanegocio.cliente.servicio;

import com.dylan.pruebanegocio.cliente.modelo.Cliente;
import com.dylan.pruebanegocio.cliente.modelo.Direccion;

import java.util.List;

public interface ClienteService {
    Cliente crearCliente(Cliente cliente);
    List<Cliente> buscarClientes(String clave);
    Direccion agregarDireccion(Long clienteId, Direccion direccion);
    List<Direccion> listarDirecciones(Long clienteId);
    Cliente editarCliente(Long clienteId, Cliente cliente);
    void eliminarCliente(Long clienteId);
}
