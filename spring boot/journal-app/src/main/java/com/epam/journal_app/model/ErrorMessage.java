package com.epam.journal_app.model;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ErrorMessage {
    private String message;
    private String path;
    private HttpStatus httpStatus;
    private LocalDateTime localDateTime;

    public ErrorMessage() {

    }

    public ErrorMessage(String message, String path, HttpStatus httpStatus, LocalDateTime localDateTime) {
        this.message = message;
        this.path = path;
        this.httpStatus = httpStatus;
        this.localDateTime = localDateTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    @Override
    public String toString() {
        return "ErrorMessage{" +
                "message='" + message + '\'' +
                ", status='" + path + '\'' +
                ", httpStatus=" + httpStatus +
                ", localDateTime=" + localDateTime +
                '}';
    }
}
