package com.epam.journal_app.exception_handler;

import com.epam.journal_app.exception.JournalNotExistsException;
import com.epam.journal_app.exception.NotAuthorizedException;
import com.epam.journal_app.model.ErrorMessage;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.Instant;
import java.time.LocalDateTime;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(value = JournalNotExistsException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessage journalNotExistHandler(Exception exception, WebRequest webRequest) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setMessage("Journal not exists for this id");
        errorMessage.setPath(webRequest.getDescription(false));
        errorMessage.setHttpStatus(HttpStatus.NOT_FOUND);
        errorMessage.setLocalDateTime(LocalDateTime.now());

        return errorMessage;
    }

    @ExceptionHandler(value = NotAuthorizedException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public ErrorMessage notAuthorizedExceptionHandler(Exception e, WebRequest webRequest) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setMessage("You are not allowed to do any activity with this record");
        errorMessage.setPath(webRequest.getDescription(false));
        errorMessage.setHttpStatus(HttpStatus.UNAUTHORIZED);
        errorMessage.setLocalDateTime(LocalDateTime.now());

        return errorMessage;
    }

    @ExceptionHandler(value = ExpiredJwtException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public ErrorMessage tokenExpiredHandler(Exception e, WebRequest webRequest) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setMessage("Your token has expired");
        errorMessage.setPath(webRequest.getDescription(false));
        errorMessage.setHttpStatus(HttpStatus.UNAUTHORIZED);
        errorMessage.setLocalDateTime(LocalDateTime.from(Instant.now()));

        return errorMessage;
    }
}
