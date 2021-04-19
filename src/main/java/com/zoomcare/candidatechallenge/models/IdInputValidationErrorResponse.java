package com.zoomcare.candidatechallenge.models;

import org.springframework.http.HttpStatus;

public class IdInputValidationErrorResponse {
  private final HttpStatus status;
  private final String message;

   public IdInputValidationErrorResponse() {
    this.status = HttpStatus.BAD_REQUEST;
    this.message = "The requested ID was not a valid value";
  }

  public HttpStatus getStatus() {
    return this.status;
  }

  public String getMessage() {
    return this.message;
  }
}
