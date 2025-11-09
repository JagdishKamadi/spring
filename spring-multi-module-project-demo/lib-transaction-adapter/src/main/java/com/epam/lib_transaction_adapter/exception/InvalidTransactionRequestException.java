package com.epam.lib_transaction_adapter.exception;

public class InvalidTransactionRequestException extends RuntimeException {

    private final String fieldName;

    public InvalidTransactionRequestException(String message) {
        super(message);
        this.fieldName = null;
    }

    public InvalidTransactionRequestException(String fieldName, String message) {
        super(message);
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }
}