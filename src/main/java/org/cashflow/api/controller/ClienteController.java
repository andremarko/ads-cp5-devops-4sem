package org.cashflow.api.controller;

import io.swagger.v3.oas.annotations.Parameter;
import org.cashflow.api.dto.ClienteRequestDTO;
import org.cashflow.api.dto.ClienteResponseDTO;
import org.cashflow.api.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ClienteResponseDTO> getAll() {
        return clienteService.getAll();
    }

    @GetMapping("/{idCliente}")
    @ResponseStatus(HttpStatus.OK)
    public ClienteResponseDTO getById(@PathVariable Long idCliente) {
        return clienteService.getById(idCliente);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteResponseDTO create(@RequestBody ClienteRequestDTO dto) {
        return clienteService.create(dto);
    }

    @PutMapping("/{idCliente}")
    @ResponseStatus(HttpStatus.OK)
    public ClienteResponseDTO update(@PathVariable Long idCliente, @RequestBody ClienteRequestDTO dto) {
        return clienteService.update(idCliente, dto);
    }

    @DeleteMapping("/{idCliente}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @Parameter(required = true)
            @PathVariable("idCliente") Long idCliente) {
        clienteService.delete(idCliente);
    }
}
