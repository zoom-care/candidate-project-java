package com.zoomcare.candidatechallenge.rest;

import com.zoomcare.candidatechallenge.NotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * Some basic exception mapping.  This class can be expanded to support a more comprehensive
 * list of exception conditions.
 */

@ControllerAdvice
public class ExceptionMapper {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> getNotFoundResponse(Exception e) {
        Map<String,String> result = new HashMap<>();
        result.put("error", e.getMessage());
        return new ResponseEntity<>(result, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }
}
