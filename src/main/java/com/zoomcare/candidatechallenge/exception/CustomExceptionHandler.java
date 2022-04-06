package com.zoomcare.candidatechallenge.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class CustomExceptionHandler extends RuntimeException {

    @Getter
    final HttpStatus httpStatus;

    public CustomExceptionHandler(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

}
