package com.zoomcare.candidatechallenge.controller;

import com.zoomcare.candidatechallenge.exception.NotFoundException;
import com.zoomcare.candidatechallenge.model.dto.ErrorDetail;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
@RestController
public class ExceptionController extends ResponseEntityExceptionHandler
{
    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<ErrorDetail> handleNotFoundException(NotFoundException ex, WebRequest request) {
        final ErrorDetail errorDetail = ErrorDetail.builder()
            .timestamp(new Date())
            .status(HttpStatus.NOT_FOUND.value())
            .message(ex.getMessage())
            .detail(request.getDescription(false))
            .build();
        return new ResponseEntity<>(errorDetail, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorDetail> handleExceptions(Exception ex, WebRequest request) {
        final ErrorDetail errorDetail = ErrorDetail.builder()
            .timestamp(new Date())
            .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
            .message(ex.getMessage())
            .detail(request.getDescription(false))
            .build();
        return new ResponseEntity<>(errorDetail, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}