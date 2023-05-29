package com.zoomcare.candidatechallenge.dto;

import java.util.Map;

/**
 * DTO to return employee with supervisor information
 */
public class EmployeeInfoDTO extends EmployeeDTO {

	/** Supervisor information **/
	private EmployeeInfoDTO supervisor;
	
	/** Error message **/
	private String error;
	
	public EmployeeInfoDTO() {
		super();
	}
	
	public EmployeeInfoDTO(String error) {
		this.error = error;
		this.setProperties(null);
	}
	
	public EmployeeInfoDTO(Long id, EmployeeInfoDTO supervisor, Map<String, String> properties) {
		super(id, properties);
		this.supervisor = supervisor;
	}
	
	public String toString() {
		return String.format("EmployeeInfo[id: %d, supervisor: %d]", getId(), null != supervisor ? supervisor.getId() : null);
	}

	public EmployeeInfoDTO getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(EmployeeInfoDTO supervisor) {
		this.supervisor = supervisor;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

}
