package org.cashflow.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.cashflow.api.model.util.TipoTransacao;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name="TBL_CAIXA")
public class Caixa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCaixa;

    @Column(name="data_transacao", nullable=false)
    @CreationTimestamp
    private LocalDateTime dataTransacao;

    @Enumerated(EnumType.STRING)
    @Column(name="tipo_transacao", nullable=false, length=50)
    private TipoTransacao tipoTransacao;

    @Column(name="valor", nullable=false, precision = 10, scale = 2)
    private BigDecimal valor;

    @Column(name="descricao", length=255)
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;
}
