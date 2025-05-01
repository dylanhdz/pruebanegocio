package com.dylan.pruebanegocio.cliente.controlador;

import com.dylan.pruebanegocio.cliente.modelo.Cliente;
import com.dylan.pruebanegocio.cliente.modelo.Direccion;
import com.dylan.pruebanegocio.cliente.servicio.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClienteController {
    private final ClienteService clienteService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente crearCliente(@RequestBody Cliente cliente) {
        return clienteService.crearCliente(cliente);
    }

    @GetMapping
    public List<Cliente> buscarClientes(@RequestParam(required = false) String clave) {
        return clienteService.buscarClientes(clave);
    }

    @PutMapping("/{clienteId}")
    public ResponseEntity<Cliente> editarCliente(
            @PathVariable Long clienteId,
            @RequestBody Cliente cliente
    ) {
        Cliente clienteActualizado = clienteService.editarCliente(clienteId, cliente);
        return ResponseEntity.ok(clienteActualizado);
    }

    @DeleteMapping("/{clienteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarCliente(@PathVariable Long clienteId) {
        clienteService.eliminarCliente(clienteId);
    }

    @PostMapping("/{clienteId}/direcciones")
    public ResponseEntity<Direccion> agregarDireccion(
            @PathVariable Long clienteId,
            @RequestBody Direccion direccion
    ) {
        Direccion nuevaDireccion = clienteService.agregarDireccion(clienteId, direccion);
        return ResponseEntity.ok(nuevaDireccion);
    }

    @GetMapping("/{clienteId}/direcciones")
    public ResponseEntity<List<Direccion>> listarDirecciones(@PathVariable Long clienteId) {
        return ResponseEntity.ok(clienteService.listarDirecciones(clienteId));
    }
}
