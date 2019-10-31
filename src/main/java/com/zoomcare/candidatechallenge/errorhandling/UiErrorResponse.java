package com.zoomcare.candidatechallenge.errorhandling;

/**
 * Represents the application error definition.
 *
 * @author Allen Wickham
 */
public class UiErrorResponse
{
    private String status;
    private String description;
    private String cause;

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getCause()
    {
        return cause;
    }

    public void setCause(String cause)
    {
        this.cause = cause;
    }
}
