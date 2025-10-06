package org.cashflow.api.exception;

public class ClienteNotFoundException extends RuntimeException {
  public ClienteNotFoundException(Long idCliente) {
    super("Cliente não encontrado: " + idCliente);
  }
}
