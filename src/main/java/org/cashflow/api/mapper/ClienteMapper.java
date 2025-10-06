package org.cashflow.api.mapper;

import org.cashflow.api.dto.ClienteRequestDTO;
import org.cashflow.api.dto.ClienteResponseDTO;
import org.cashflow.api.model.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
    ClienteResponseDTO toResponseDTO(Cliente cliente);
    Cliente toEntity(ClienteRequestDTO dto);
    void updateEntityFromDto(ClienteRequestDTO dto, @MappingTarget Cliente cliente);
}
