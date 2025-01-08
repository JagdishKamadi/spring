package com.epam.journal_app.exception;

public class JournalNotExistsException extends RuntimeException {

    public JournalNotExistsException() {
        super();
    }

    public JournalNotExistsException(String message) {
        super(message);
    }
}
