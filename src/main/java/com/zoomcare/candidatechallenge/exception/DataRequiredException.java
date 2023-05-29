package com.zoomcare.candidatechallenge.exception;

import org.springframework.http.HttpStatus;

/**
 * Exception to report data required
 */
public class DataRequiredException extends EmployeeException {

	private static final long serialVersionUID = 1L;
	
	public DataRequiredException(String message) {
		super();
		this.message = message;
		this.httpStatus = HttpStatus.BAD_REQUEST;
	}

}
