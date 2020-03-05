package com.zoomcare.candidatechallenge.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class InvalidDataTypeExceptionMapper implements ExceptionMapper<InvalidDataTypeException> {
    @Override
    public Response toResponse(InvalidDataTypeException e) {
        return Response.status(
                Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
    }
}
