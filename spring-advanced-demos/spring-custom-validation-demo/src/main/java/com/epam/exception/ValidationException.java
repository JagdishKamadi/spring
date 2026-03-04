package com.epam.exception;

import lombok.Getter;

@Getter
public class ValidationException extends RuntimeException {

    private String apiField;

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(String message, String apiField) {
        super(message);
        this.apiField = apiField;
    }

}