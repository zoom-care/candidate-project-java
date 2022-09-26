package com.zoomcare.candidatechallenge.exception;

public class UnexpectedException extends BusinessException{

    public UnexpectedException() {
    }

    public UnexpectedException(String message) {
        super(message);
    }

    public UnexpectedException(Throwable cause) {
        super(cause);
    }

    public UnexpectedException(String message, Throwable cause) {
        super(message, cause);
    }
}
