package org.cashflow.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ClienteRequestDTO {
    @NotBlank
    @Schema(
            description = "Username",
            example="johndoe"
    )
    private String nome;
    @Schema(
            description = "CPF do cliente",
            example="12345678912"
    )
    @NotBlank
    private String cpf;
}
