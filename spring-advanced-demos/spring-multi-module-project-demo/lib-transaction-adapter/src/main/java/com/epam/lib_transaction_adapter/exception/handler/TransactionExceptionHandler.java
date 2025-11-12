package com.epam.lib_transaction_adapter.exception.handler;

import com.epam.lib_transaction_adapter.exception.InvalidTransactionRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Global exception handler for transaction-related exceptions.
 * <p>
 * Captures and formats validation or processing errors into
 * structured HTTP responses.
 * </p>
 */
@ControllerAdvice
public class TransactionExceptionHandler {

    /**
     * Handles {@link InvalidTransactionRequestException} and returns
     * a standardized error response with HTTP 400 status.
     *
     * @param exception  the thrown exception instance
     * @param webRequest the current web request context
     * @return a structured error response entity
     */
    @ExceptionHandler(InvalidTransactionRequestException.class)
    public ResponseEntity<Map<String, Object>> handleInvalidTransactionRequest(
            InvalidTransactionRequestException exception, WebRequest webRequest) {

        Map<String, Object> errorResponse = new LinkedHashMap<>();
        errorResponse.put("timestamp", LocalDateTime.now());
        errorResponse.put("status", HttpStatus.BAD_REQUEST.value());
        errorResponse.put("error", HttpStatus.BAD_REQUEST.getReasonPhrase());
        errorResponse.put("message", exception.getMessage());
        errorResponse.put("path", exception.getFieldName() != null
                ? exception.getFieldName()
                : webRequest.getDescription(false).replace("uri=", ""));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
}