package org.cashflow.api.mapper;

/*


*/

import org.cashflow.api.dto.CaixaRequestDTO;
import org.cashflow.api.dto.CaixaResponseDTO;
import org.cashflow.api.model.Caixa;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CaixaMapper {
    CaixaResponseDTO toResponseDTO(Caixa caixa);
    Caixa toEntity(CaixaRequestDTO dto);
    void updateEntityFromDto(CaixaRequestDTO dto, @MappingTarget Caixa caixa);
}
