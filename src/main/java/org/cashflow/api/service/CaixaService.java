package org.cashflow.api.service;

import org.cashflow.api.dto.CaixaRequestDTO;
import org.cashflow.api.dto.CaixaResponseDTO;
import org.cashflow.api.exception.CaixaNotFoundException;
import org.cashflow.api.exception.ClienteNotFoundException;
import org.cashflow.api.mapper.CaixaMapper;
import org.cashflow.api.model.Caixa;
import org.cashflow.api.model.Cliente;
import org.cashflow.api.repository.CaixaRepository;
import org.cashflow.api.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CaixaService {

    @Autowired
    private CaixaRepository caixaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private CaixaMapper caixaMapper;

    public List<CaixaResponseDTO> getAll() {
        List<Caixa> caixas = caixaRepository.findAll();
        return caixas.stream()
                .map(caixaMapper::toResponseDTO)
                .toList();
    }

    public CaixaResponseDTO getById(Long caixaId) throws CaixaNotFoundException {
        Caixa caixa = caixaRepository.findById(caixaId)
                .orElseThrow(() -> new CaixaNotFoundException(caixaId));
        return caixaMapper.toResponseDTO(caixa);
    }

    public CaixaResponseDTO create(CaixaRequestDTO dto) {
        Cliente cliente = clienteRepository.findById(dto.getIdCliente())
                .orElseThrow(() -> new ClienteNotFoundException(dto.getIdCliente()));

        Caixa caixa = caixaMapper.toEntity(dto);
        caixa.setCliente(cliente);
        Caixa created = caixaRepository.save(caixa);
        return caixaMapper.toResponseDTO(created);
    }

    public CaixaResponseDTO update(Long caixaId, CaixaRequestDTO dto) {
        Caixa caixa = caixaRepository.findById(caixaId)
                .orElseThrow(() -> new CaixaNotFoundException(caixaId));
        caixaMapper.updateEntityFromDto(dto, caixa);
        Caixa updated = caixaRepository.save(caixa);
        return caixaMapper.toResponseDTO(updated);
    }

    public void delete(Long caixaId) {
        Caixa caixa = caixaRepository.findById(caixaId)
                .orElseThrow(() -> new CaixaNotFoundException(caixaId));
        try {
            caixaRepository.delete(caixa);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException(e.getMessage());
        }
    }
}
