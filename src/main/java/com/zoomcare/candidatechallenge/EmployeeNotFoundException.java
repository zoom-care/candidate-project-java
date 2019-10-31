package com.zoomcare.candidatechallenge.exception;

public class EmployeeNotFoundException extends Exception {

    private long employee_id;

    public EmployeeNotFoundException(long employee_id) {
        super(String.format("Employee ID '%s' not found", employee_id));
    }
}
