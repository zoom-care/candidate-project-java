package com.zoomcare.candidatechallenge.controller.helpers;

import com.zoomcare.candidatechallenge.exceptions.EmployeeInternalServerError;
import com.zoomcare.candidatechallenge.exceptions.EmployeeNotFound;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ControllerExceptionHandler {
    @ExceptionHandler(EmployeeInternalServerError.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleAppException(EmployeeInternalServerError ex) {
        log.error(ex.getLocalizedMessage());
        return ex.getLocalizedMessage();
    }

    @ExceptionHandler(EmployeeNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleAppException(EmployeeNotFound ex) {
        log.error(ex.getLocalizedMessage());
        return ex.getLocalizedMessage();
    }
}
