package com.zoomcare.candidatechallenge.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PropertyNotFoundException extends RuntimeException {
    public PropertyNotFoundException(String msg) {
        super(msg);
    }
}
