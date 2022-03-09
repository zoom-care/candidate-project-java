package com.zoomcare.candidatechallenge.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * SystemGeneralException class
 * @author aquintero
 */
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class SystemGeneralException extends RuntimeException
{
    private static final long serialVersionUID = 1L;

    public SystemGeneralException(String message)
    {
        super(ErrorMessages.ERROR_GENERAL_SYSTEM + " - " + message);
    }

    public SystemGeneralException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public SystemGeneralException(Throwable cause)
    {
        super(cause);
    }

}
