package com.zoomcare.candidatechallenge.controller;

import com.zoomcare.candidatechallenge.exception.DataNotFoundException;
import com.zoomcare.candidatechallenge.exception.UnexpectedException;
import com.zoomcare.candidatechallenge.model.dto.CommonResponse;
import com.zoomcare.candidatechallenge.model.enums.ResponseCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler({Exception.class})
    public ResponseEntity<CommonResponse> handleException(Exception exception) {

        if (exception instanceof DataNotFoundException) {
            return handleDataNotFound((DataNotFoundException) exception);
        } else if (exception instanceof UnexpectedException) {
            return handleUnexpectedException((UnexpectedException) exception);
        } else {
            return handleUnexpectedException(new UnexpectedException("Something went wrong", exception));
        }
    }

    private ResponseEntity<CommonResponse> handleDataNotFound(DataNotFoundException exception) {
        return createBasicResponse(ResponseCode.DATA_NOT_FOUND, exception, HttpStatus.NOT_FOUND);
    }

    private ResponseEntity<CommonResponse> handleUnexpectedException(UnexpectedException exception) {
        return createBasicResponse(ResponseCode.UNEXPECTED_ERROR, exception, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<CommonResponse> createBasicResponse(ResponseCode code, Exception exception,
                                                               HttpStatus status) {
        return new ResponseEntity<>(new CommonResponse(code, exception.getMessage()), status);
    }
}
