package com.zoomcare.candidatechallenge.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ControllerAdvice
@Log4j2
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {CustomExceptionHandler.class})
    protected ResponseEntity<Object> handleCustomException(CustomExceptionHandler ex, WebRequest request) {
        log.error("Custom exception was throw: [{}]", ex.getMessage());
        return handleExceptionInternal(ex, "Internal exception has occurred", new HttpHeaders(), ex.getHttpStatus(), request);
    }

    @ExceptionHandler(value = {RuntimeException.class})
    protected ResponseEntity<Object> handleCustomException(RuntimeException ex, WebRequest request) {

        log.error("Internal exception has occurred: [{}]", ex.getMessage());
        return handleExceptionInternal(ex, "Internal exception has occurred", new HttpHeaders(), INTERNAL_SERVER_ERROR, request);
    }
}
