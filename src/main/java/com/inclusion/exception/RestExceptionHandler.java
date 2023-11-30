package com.inclusion.exception;

import com.inclusion.dto.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;


@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value = {RuntimeException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse handleBadRequestExceptions(final Exception exception, WebRequest request) {
        return new ExceptionResponse(
                exception.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now());
    }
}
