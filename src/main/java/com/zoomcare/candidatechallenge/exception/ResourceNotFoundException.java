package com.zoomcare.candidatechallenge.exception;

@SuppressWarnings("serial")
public class ResourceNotFoundException extends RuntimeException{
	
	public ResourceNotFoundException(final String message) {
        super(message);
    }

}
