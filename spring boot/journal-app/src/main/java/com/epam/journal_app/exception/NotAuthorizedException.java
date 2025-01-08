package com.epam.journal_app.exception;

public class NotAuthorizedException extends RuntimeException {

    public NotAuthorizedException() {
        super();
    }

    public NotAuthorizedException(String message) {
        super(message);
    }
}
