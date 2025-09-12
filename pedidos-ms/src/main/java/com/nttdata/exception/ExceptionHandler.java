package com.nttdata.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionHandler {

        @org.springframework.web.bind.annotation.ExceptionHandler(IllegalArgumentException.class)
        public ResponseEntity<Object> handleBadRequest(IllegalArgumentException ex) {
            Map<String, Object> body = new HashMap<>();
            body.put("mensaje", ex.getMessage());
            return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
        }

        @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
        public ResponseEntity<Object> handleInternalServerError(Exception ex) {
            Map<String, Object> body = new HashMap<>();
            body.put("mensaje", "Error interno del servidor");
            return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<Object> handleValidation(MethodArgumentNotValidException ex) {
            Map<String, String> errores = new HashMap<>();
            ex.getBindingResult().getFieldErrors().forEach(error ->
                    errores.put(error.getField(), error.getDefaultMessage())
            );
            return new ResponseEntity<>(errores, HttpStatus.BAD_REQUEST);
        }

}
