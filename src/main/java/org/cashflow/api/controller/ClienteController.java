package org.cashflow.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.cashflow.api.dto.ClienteRequestDTO;
import org.cashflow.api.dto.ClienteResponseDTO;
import org.cashflow.api.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cliente")
@Tag(name = "Cliente", description = "API para cadastro de um Cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    @Operation(summary = "Lista todos os clientes")
    @ResponseStatus(HttpStatus.OK)
    public List<ClienteResponseDTO> getAll() {
        return clienteService.getAll();
    }

    @GetMapping("/{idCliente}")
    @Operation(summary = "Busca um cliente por ID")
    @ResponseStatus(HttpStatus.OK)
    public ClienteResponseDTO getById(@PathVariable Long idCliente) {
        return clienteService.getById(idCliente);
    }

    @PostMapping
    @Operation(summary = "Cadastra um cliente")
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteResponseDTO create(@RequestBody ClienteRequestDTO dto) {
        return clienteService.create(dto);
    }

    @PutMapping("/{idCliente}")
    @Operation(summary = "Atualiza um cliente por ID")
    @ResponseStatus(HttpStatus.OK)
    public ClienteResponseDTO update(@PathVariable Long idCliente, @RequestBody ClienteRequestDTO dto) {
        return clienteService.update(idCliente, dto);
    }

    @DeleteMapping("/{idCliente}")
    @Operation(summary = "Deleta um cliente")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @Parameter(required = true)
            @PathVariable("idCliente") Long idCliente) {
        clienteService.delete(idCliente);
    }
}
