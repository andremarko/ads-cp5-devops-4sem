package org.cashflow.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cashflow.api.model.util.TipoTransacao;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CaixaResponseDTO {
    private Long idCaixa;
    private LocalDateTime dataTransacao;
    private TipoTransacao tipoTransacao;
    private Double valor;
    private String descricao;
}
