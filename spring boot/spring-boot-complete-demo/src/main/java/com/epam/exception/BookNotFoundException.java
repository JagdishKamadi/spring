package com.epam.exception;

public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException() {
        super("Book not found for this id");
    }

    public BookNotFoundException(String message) {
        super(message);
    }

    public BookNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

