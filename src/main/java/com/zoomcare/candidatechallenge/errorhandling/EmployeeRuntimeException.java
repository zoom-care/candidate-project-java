package com.zoomcare.candidatechallenge.errorhandling;

public final class EmployeeRuntimeException extends RuntimeException
{
    private final int status;

    public EmployeeRuntimeException(final String message, final int status)
    {
        super(message);
        this.status = status;
    }

    public EmployeeRuntimeException(final String message, final Throwable cause, final int status)
    {
        super(message, cause);
        this.status = status;
    }

    public int getStatus()
    {
        return status;
    }
}
