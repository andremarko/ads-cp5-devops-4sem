package org.cashflow.api.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ClienteNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleClienteNotFound(ClienteNotFoundException ex) {
        return respostaErro(HttpStatus.NOT_FOUND, "Cliente não encontrado", ex.getMessage());
    }

    @ExceptionHandler(CaixaNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleCaixaNotFound(CaixaNotFoundException ex) {
        return respostaErro(HttpStatus.NOT_FOUND, "Caixa não encontrado", ex.getMessage());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, Object>> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        return respostaErro(HttpStatus.BAD_REQUEST, "Violação de integridade de dados", ex.getMostSpecificCause().getMessage());
    }

    private ResponseEntity<Map<String, Object>> respostaErro(HttpStatus status, String erro, String mensagem) {
        Map<String, Object> body = new HashMap<>();
        body.put("status", status.value());
        body.put("erro", erro);
        body.put("mensagem", mensagem);
        body.put("timestamp", LocalDateTime.now());
        return ResponseEntity.status(status).body(body);
    }
}
