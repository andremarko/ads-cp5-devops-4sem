package org.cashflow.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.cashflow.api.dto.CaixaRequestDTO;
import org.cashflow.api.dto.CaixaResponseDTO;
import org.cashflow.api.service.CaixaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/caixa")
@Tag(name = "Caixa", description = "API para cadastro e manipulação de um fluxo de caixa")
public class CaixaController {

    @Autowired
    private CaixaService caixaService;

    @Operation(summary = "Lista todos os fluxos de caixa")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CaixaResponseDTO> getAll() {
        return caixaService.getAll();
    }

    @Operation(summary = "Busca um fluxo de caixa por ID")
    @GetMapping("/{idCaixa}")
    @ResponseStatus(HttpStatus.OK)
    public CaixaResponseDTO getById(@PathVariable Long idCaixa) {
        return caixaService.getById(idCaixa);
    }

    @Operation(summary = "Cadastra um fluxo de caixa")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CaixaResponseDTO create(@RequestBody CaixaRequestDTO dto) {
        return caixaService.create(dto);
    }

    @Operation(summary = "Atualiza um fluxo de caixa")
    @PutMapping("/{idCaixa}")
    @ResponseStatus(HttpStatus.OK)
    public CaixaResponseDTO update(@PathVariable Long idCaixa, @RequestBody CaixaRequestDTO dto) {
        return caixaService.update(idCaixa,dto);
    }

    @Operation(summary = "Deleta um fluxo de caixa")
    @DeleteMapping("/{idCaixa}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @Parameter(required = true)
            @PathVariable("idCaixa") Long idCaixa) {
        caixaService.delete(idCaixa);
    }
}
