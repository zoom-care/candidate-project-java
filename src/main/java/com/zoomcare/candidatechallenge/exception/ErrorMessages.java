package com.zoomcare.candidatechallenge.exception;

/**
 * ErrorMessages class
 * @author aquintero
 */
public class ErrorMessages
{

    public static final String ERROR_CONVERSION = "Error when converting the object";

    public static final String HTTP_STATUS_CONFLICT_ERROR_MESSAGE = "Already Exists!!!";

    public static final String ERROR_GENERAL_SYSTEM = "An unexpected error has occurred";
    public static final String ERROR_FIELD_FORMAT_MESSAGE = "Invalid format";
    public static final String RECORD_NOT_FOUND = "Cannot find record";
    public static final String TABLE_ALREADY_EXIST = "Table already exist!!!";

    public static final String URL_INSERT_TABLE_BIGQUERY_NOT_FOUND = "Insert table bigquery url not found!!!";
    public static final String URL_INSERT_DATASET_BIGQUERY_NOT_FOUND = "Insert dataset bigquery url not found!!!";

    private ErrorMessages()
    {
        throw new IllegalStateException("Utility class");
    }


}
