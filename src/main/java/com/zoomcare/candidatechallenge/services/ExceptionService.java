package com.zoomcare.candidatechallenge.services;

import com.zoomcare.candidatechallenge.models.IdInputValidationErrorResponse;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ExceptionService extends ResponseEntityExceptionHandler {
  
  @ExceptionHandler(NumberFormatException.class)
  protected ResponseEntity<Object> handleNumberFormatException(NumberFormatException ex) {
    return buildResponse(new IdInputValidationErrorResponse());
  }

  private ResponseEntity<Object> buildResponse(IdInputValidationErrorResponse response) {
    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }
}
