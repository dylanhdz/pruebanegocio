package com.dylan.pruebanegocio.cliente.servicio;

import com.dylan.pruebanegocio.cliente.modelo.Cliente;

import java.util.List;

public interface ClienteService {
    Cliente crearCliente(Cliente cliente);
    List<Cliente> buscarClientes(String id);
}
