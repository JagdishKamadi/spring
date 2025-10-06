package com.epam.model;

import org.springframework.http.HttpStatus;

public record ErrorMessage(String message, String path, String timeStamp, HttpStatus status) {
}
