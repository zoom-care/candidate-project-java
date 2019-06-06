package com.zoomcare.candidatechallenge.exception;

public class EmployeeNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -4952702389220476758L;

    public EmployeeNotFoundException(String msg) {
        super(msg);
    }
}
