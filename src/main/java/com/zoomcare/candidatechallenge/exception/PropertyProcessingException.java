package com.zoomcare.candidatechallenge.exception;

import com.zoomcare.candidatechallenge.model.Property;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class PropertyProcessingException extends Exception {

    public PropertyProcessingException(String message) {
        super(message);
    }
}
