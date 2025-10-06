package org.cashflow.api.exception;

public class CaixaNotFoundException extends RuntimeException {
    public CaixaNotFoundException(Long idCaixa) {
        super("Caixa não encontrado: " + idCaixa);
    }
}
