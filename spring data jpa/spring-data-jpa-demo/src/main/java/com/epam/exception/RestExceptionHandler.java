package com.epam.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDate.now());
        body.put("status", status);

        // now put all error in list
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());
        body.put("errors", errors);

        return new ResponseEntity<>(body, status);
    }

    @ExceptionHandler(value = {EmployeeNotFoundException.class})
    public ResponseEntity<ErrorMessage> EmployeeNotFoundExceptionHandler(EmployeeNotFoundException exception,WebRequest request)
    {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setTimestamp(LocalDate.now().toString());
        errorMessage.setStatus(HttpStatus.NOT_FOUND.toString());
        errorMessage.setError(exception.getMessage());
        errorMessage.setPath(request.getDescription(false));

        return new ResponseEntity<>(errorMessage,HttpStatus.NOT_FOUND);
    }
}
