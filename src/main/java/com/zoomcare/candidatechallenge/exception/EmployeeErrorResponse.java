package com.zoomcare.candidatechallenge.exception;

public class EmployeeErrorResponse {

	private int status;
	private String message;
	private long timeStamp;

	public EmployeeErrorResponse() {
	}

	public EmployeeErrorResponse(int status, String message, long timeStamp) {
		this.status = status;
		this.message = message;
		this.timeStamp = timeStamp;
	}

	public int getStatus() {
		return status;
	}

	public EmployeeErrorResponse setStatus(int status) {
		this.status = status;
		return this;
	}

	public String getMessage() {
		return message;
	}

	public EmployeeErrorResponse setMessage(String message) {
		this.message = message;
		return this;
	}

	public long getTimeStamp() {
		return timeStamp;
	}

	public EmployeeErrorResponse setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
		return this;
	}

	@Override
	public String toString() {
		return String.format("EmployeeErrorResponse [status=%s, message=%s, timeStamp=%s]", status, message, timeStamp);
	}

}
