package com.zoomcare.candidatechallenge.exception;

import org.springframework.http.HttpStatus;

import com.zoomcare.candidatechallenge.constants.ApplicationConstants;

public class EmployeeNotFoundException extends EmployeeException {
	
	private static final long serialVersionUID = 1L;

	public EmployeeNotFoundException(Long emplooyeeId) {
		super();
		message = String.format(ApplicationConstants.EMPLOYEE_NOT_FOUND, emplooyeeId);
		httpStatus = HttpStatus.NOT_FOUND;
	}
	
}
