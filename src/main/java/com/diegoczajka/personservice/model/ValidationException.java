package com.diegoczajka.personservice.model;

public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }
}
