package org.cashflow.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteResponseDTO {
    private Long idCliente;
    private String nome;
    private String cpf;
    private LocalDateTime criadoEm;
}
