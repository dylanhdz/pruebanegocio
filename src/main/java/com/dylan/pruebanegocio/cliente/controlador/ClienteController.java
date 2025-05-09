package com.dylan.pruebanegocio.cliente.controlador;

import com.dylan.pruebanegocio.cliente.modelo.Cliente;
import com.dylan.pruebanegocio.cliente.modelo.Direccion;
import com.dylan.pruebanegocio.cliente.servicio.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/clientes")
@Tag(name = "Cliente", description = "Operaciones de gestión de clientes y direcciones")
@RequiredArgsConstructor
public class ClienteController {
    private final ClienteService clienteService;
    @Operation(summary = "Crear un nuevo cliente")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente crearCliente(@Valid @RequestBody Cliente cliente) {
        return clienteService.crearCliente(cliente);
    }

    @Operation(summary = "Buscar clientes por nombre o número de identificación")
    @GetMapping
    public List<Cliente> buscarClientes(@RequestParam(required = false) String clave) {
        return clienteService.buscarClientes(clave);
    }

    @Operation(summary = "Editar un cliente existente")
    @PutMapping("/{clienteId}")
    public ResponseEntity<Cliente> editarCliente(
            @PathVariable Long clienteId,
            @RequestBody Cliente cliente
    ) {
        Cliente clienteActualizado = clienteService.editarCliente(clienteId, cliente);
        return ResponseEntity.ok(clienteActualizado);
    }

    @Operation(summary = "Eliminar un cliente")
    @DeleteMapping("/{clienteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarCliente(@PathVariable Long clienteId) {
        clienteService.eliminarCliente(clienteId);
    }

    @Operation(summary = "Agregar una dirección a un cliente")
    @PostMapping("/{clienteId}/direcciones")
    public ResponseEntity<Direccion> agregarDireccion(
            @PathVariable Long clienteId,
            @RequestBody Direccion direccion
    ) {
        Direccion nuevaDireccion = clienteService.agregarDireccion(clienteId, direccion);
        return ResponseEntity.ok(nuevaDireccion);
    }

    @Operation(summary = "Listar direcciones de un cliente")
    @GetMapping("/{clienteId}/direcciones")
    public ResponseEntity<List<Direccion>> listarDirecciones(@PathVariable Long clienteId) {
        return ResponseEntity.ok(clienteService.listarDirecciones(clienteId));
    }
}
