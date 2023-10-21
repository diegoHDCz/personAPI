package com.diegoczajka.personservice.config.exception;

import com.diegoczajka.personservice.model.ValidationException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity handleError404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleError400(MethodArgumentNotValidException ex) {
        var erros = ex.getFieldErrors();

        return ResponseEntity.badRequest().body(erros.stream().map(ErrorValidationData::new).toList());
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity handleErrorEntties(ValidationException ex) {
        return ResponseEntity.unprocessableEntity().body(ex.getMessage());
    }

    private record ErrorValidationData(String campo, String mensagem) {

        public ErrorValidationData(FieldError erro) {
            this(erro.getField(), erro.getDefaultMessage());
        }

    }
}
