package org.cashflow.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cashflow.api.model.util.TipoTransacao;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CaixaRequestDTO {
    @Schema(
            description = "Tipo da transação (ENTRADA OU SAIDA)",
            example = "ENTRADA"
    )
    @NotNull
    private TipoTransacao tipoTransacao;
    @Schema(
            description = "Valor da transação",
            example="125.75"
    )
    @NotNull
    @Positive
    private BigDecimal valor;
    @Schema(
            description="Descrição da transação",
            example="Compra de ração para o estoque\""
    )
    private String descricao;
    @Schema(
            description = "ID do cliente relacionado à transação",
            example="1"
    )
    @NotNull
    private Long idCliente;
}
