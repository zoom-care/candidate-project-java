package com.zoomcare.candidatechallenge.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class EmployeeProcessingException extends Exception {
    public EmployeeProcessingException(String message, Throwable cause) {
        super(message, cause);
    }
}
