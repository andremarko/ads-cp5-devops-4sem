package org.cashflow.api.controller;

import io.swagger.v3.oas.annotations.Parameter;
import org.cashflow.api.dto.CaixaRequestDTO;
import org.cashflow.api.dto.CaixaResponseDTO;
import org.cashflow.api.service.CaixaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/caixa")
public class CaixaController {

    @Autowired
    private CaixaService caixaService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CaixaResponseDTO> getAll() {
        return caixaService.getAll();
    }

    @GetMapping("/{idCaixa}")
    @ResponseStatus(HttpStatus.OK)
    public CaixaResponseDTO getById(@PathVariable Long idCaixa) {
        return caixaService.getById(idCaixa);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CaixaResponseDTO create(@RequestBody CaixaRequestDTO dto) {
        return caixaService.create(dto);
    }

    @PutMapping("/{idCaixa}")
    @ResponseStatus(HttpStatus.OK)
    public CaixaResponseDTO update(@PathVariable Long idCaixa, @RequestBody CaixaRequestDTO dto) {
        return caixaService.update(idCaixa,dto);
    }

    @DeleteMapping("/{idCaixa}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @Parameter(required = true)
            @PathVariable("idCaixa") Long idCaixa) {
        caixaService.delete(idCaixa);
    }
}
