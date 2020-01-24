package com.zoomcare.candidatechallenge.exception;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(Long id) {
        super("Employee with ID " + id + " was not found.");
    }
}
