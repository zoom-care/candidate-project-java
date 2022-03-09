package com.zoomcare.candidatechallenge.exception;

/**
 * ErrorMessages class
 * @author aquintero
 */
public class ErrorMessages
{

    public static final String ERROR_CONVERSION = "Error when converting the object";
    public static final String ERROR_GENERAL_SYSTEM = "An unexpected error has occurred";
    public static final String ERROR_FIELD_FORMAT_MESSAGE = "Invalid format";

    private ErrorMessages()
    {
        throw new IllegalStateException("Utility class");
    }


}
