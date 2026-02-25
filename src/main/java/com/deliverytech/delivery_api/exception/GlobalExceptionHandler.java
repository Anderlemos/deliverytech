package com.deliverytech.delivery_api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Email já cadastrado
    @ExceptionHandler(EmailJaCadastradoException.class)
    public ResponseEntity<Object> handleEmailDuplicado(EmailJaCadastradoException ex) {

        return buildResponse(HttpStatus.CONFLICT, ex.getMessage());
    }

    // Recurso não encontrado
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleNotFound(ResourceNotFoundException ex) {

        return buildResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    // Erro de validação (@Valid)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidation(MethodArgumentNotValidException ex) {

        String mensagem = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getDefaultMessage())
                .findFirst()
                .orElse("Erro de validação");

        return buildResponse(HttpStatus.BAD_REQUEST, mensagem);
    }

    // 🔴 Erro genérico
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGeneric(Exception ex) {

        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Erro interno no servidor");
    }

    // 🔧 Método auxiliar para padronizar resposta
    private ResponseEntity<Object> buildResponse(HttpStatus status, String message) {

        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", status.value());
        body.put("error", status.getReasonPhrase());
        body.put("message", message);

        return new ResponseEntity<>(body, status);
    }
}