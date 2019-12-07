package com.zoomcare.candidatechallenge.exception;

public class EmployeeNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -3797397124787655365L;

	public EmployeeNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public EmployeeNotFoundException(String message) {
		super(message);
	}

	public EmployeeNotFoundException(Throwable cause) {
		super(cause);
	}
}