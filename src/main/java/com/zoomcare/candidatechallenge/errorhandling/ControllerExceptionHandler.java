package com.zoomcare.candidatechallenge.errorhandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Provides exception handling functionality.
 *
 * @author Allen Wickham
 */
@ControllerAdvice
public class ControllerExceptionHandler
{
    /**
     * Handle the {@link EmployeeRuntimeException}s and convert them into a UI error response.
     *
     * @param runtimeException The exception to map into a UI error response. Must not be {@code null}.
     *
     * @return A response mapped to the given exception. Will never be {@code null}.
     */
    @ExceptionHandler(EmployeeRuntimeException.class)
    public ResponseEntity<UiErrorResponse> exceptionHandler(final EmployeeRuntimeException runtimeException)
    {
        final UiErrorResponse errorResponse = new UiErrorResponse();
        final int status = runtimeException.getStatus();

        errorResponse.setStatus(Integer.toString(status));
        errorResponse.setDescription(runtimeException.getMessage());
        errorResponse.setCause(null != runtimeException.getCause() ? runtimeException.getCause().getMessage() : null);

        return new ResponseEntity<UiErrorResponse>(errorResponse, HttpStatus.valueOf(status));
    }
}
