package com.zoomcare.candidatechallenge.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class UserDoesNotExistMapper implements ExceptionMapper<UserDoesNotExistException> {
    @Override
    public Response toResponse(UserDoesNotExistException e) {
        return Response.status(
                Response.Status.NOT_FOUND).entity(e.getMessage()).build();
    }
}
