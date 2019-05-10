package com.zoomcare.candidatechallenge.web;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.zoomcare.candidatechallenge.service.EmployeeNotFoundException;

@ControllerAdvice

public class RestExceptionHandler

		extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value

	= { EmployeeNotFoundException.class })

	protected ResponseEntity<Object> handleConflict(

			EmployeeNotFoundException ex, WebRequest request) {

		String bodyOfResponse = "No Employee found for the passed in Employee Id.";

		return handleExceptionInternal(ex, bodyOfResponse,

				new HttpHeaders(), HttpStatus.NOT_FOUND, request);

	}

}
