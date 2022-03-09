package com.zoomcare.candidatechallenge.exception.enums;

import lombok.Getter;

/**
 * CustomExceptionHandlerEnum enum
 * @author aquintero
 */
@Getter
public enum CustomExceptionHandlerEnum
{

    BAD_REQUEST("Bad Request"),
    FORBIDDEN("Forbidden"),
    UNAUTHORIZED("Unauthorized"),
    NOT_FOUND("Not Found"),
    INTERNAL_SERVER_ERROR("Internal Server Error");

    private String description;

    private CustomExceptionHandlerEnum(String description) {
        this.description = description;
    }

}
