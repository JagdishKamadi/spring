package com.epam.lib_transaction_adapter.exception;

public class InvalidTransactionRequestException extends RuntimeException {
    public InvalidTransactionRequestException() {
        super("Invalid Request!");
    }

    public InvalidTransactionRequestException(String message) {
        super(message);
    }
}
