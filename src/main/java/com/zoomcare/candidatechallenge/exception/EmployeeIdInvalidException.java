package com.zoomcare.candidatechallenge.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmployeeIdInvalidException extends Exception {

    public EmployeeIdInvalidException(String message) {
        super(message);
    }
}
