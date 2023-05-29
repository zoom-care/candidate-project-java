package com.zoomcare.candidatechallenge.dto;

import java.util.Map;

/**
 * DTO to create organization estructure
 */
public class OrganizationStructureDTO extends EmployeeDTO {
	
	/** Supervisor ID **/
	private Long supervisor;

	public OrganizationStructureDTO() {
		super();
	}

	public OrganizationStructureDTO(Long id, Map<String, String> properties, Long supervisor) {
		super(id, properties);
		this.supervisor = supervisor;
	}
	
	@Override
	public String toString() {
		return String.format("OrganizationStructure[id; %d, supervidor: %d]", getId(), supervisor);
	}

	public Long getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(Long supervisor) {
		this.supervisor = null != supervisor ? supervisor : -1l;
	}
	
}
