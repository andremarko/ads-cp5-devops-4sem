package org.cashflow.api.service;

import org.cashflow.api.dto.ClienteRequestDTO;
import org.cashflow.api.dto.ClienteResponseDTO;
import org.cashflow.api.exception.ClienteNotFoundException;
import org.cashflow.api.mapper.ClienteMapper;
import org.cashflow.api.model.Cliente;
import org.cashflow.api.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteMapper clienteMapper;

    // sempre retorna uma lista de clientes
    public List<ClienteResponseDTO> getAll()  {
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes.stream().map(clienteMapper :: toResponseDTO).toList();
    }

    public ClienteResponseDTO getById (Long idCliente) throws ClienteNotFoundException {
        Cliente cliente = clienteRepository.findById(idCliente)
                .orElseThrow(() -> new ClienteNotFoundException(idCliente));
        return clienteMapper.toResponseDTO(cliente);
    }

    public ClienteResponseDTO create(ClienteRequestDTO dto) {
        // transforma dto para entidade
        Cliente cliente = clienteMapper.toEntity(dto);
        Cliente created = clienteRepository.save(cliente);
        return clienteMapper.toResponseDTO(created);
    }

    public ClienteResponseDTO update(Long clienteId, ClienteRequestDTO dto) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new ClienteNotFoundException(clienteId));
        clienteMapper.updateEntityFromDto(dto, cliente);

        Cliente updated = clienteRepository.save(cliente);

        return clienteMapper.toResponseDTO(updated);
    }

    public void delete(Long clienteId) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new ClienteNotFoundException(clienteId));
        try {
            clienteRepository.delete(cliente);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException(e.getMessage());
        }
    }
}
