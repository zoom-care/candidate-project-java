package com.zoomcare.candidatechallenge.exception;

import org.springframework.http.HttpStatus;

/**
 * Basic exception for employee operations
 */
public class EmployeeException extends Exception {

	private static final long serialVersionUID = 1L;
	
	/** Error message **/
	protected String message;
	
	/** Status response **/
	protected HttpStatus httpStatus;
	
	public EmployeeException() {
		super();
	}

	public EmployeeException(String message, HttpStatus httpStatus) {
		super();
		this.message = message;
		this.httpStatus = httpStatus;
	}

	public String getMessage() {
		return message;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

}
