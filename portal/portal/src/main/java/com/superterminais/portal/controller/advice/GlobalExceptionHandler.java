package com.superterminais.portal.controller.advice;

import com.superterminais.portal.exception.RegistrationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RegistrationException.class)
    public ResponseEntity<Map<String, String>> handleRegistrationException(RegistrationException ex) {
        // Creates a simple JSON object: {"error": "The exception message"}
        Map<String, String> errorResponse = Map.of("error", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<Map<String, String>> handleMaxSizeException(MaxUploadSizeExceededException ex) {
        Map<String, String> errorResponse = Map.of("error", "Tamanho de arquivo não suportado.");
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}