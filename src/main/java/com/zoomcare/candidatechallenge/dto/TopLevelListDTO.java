package com.zoomcare.candidatechallenge.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * DTO to create top level list organization
 */
public class TopLevelListDTO extends EmployeeDTO {
		
	/** underling employees **/
	private List<TopLevelListDTO> underling;

	public TopLevelListDTO() {
		super();
		underling = new ArrayList<>();
	}

	public TopLevelListDTO(Long id, Map<String, String> properties, List<TopLevelListDTO> underling) {
		super(id, properties);
		this.underling = underling;
	}
	
	public String toString() {
		return String.format("TopLevelLis[id: %d, underling: %d]", getId(), underling.size());
	}

	public List<TopLevelListDTO> getUnderling() {
		return underling;
	}

	public void setUnderling(List<TopLevelListDTO> underling) {
		this.underling = underling;
	}
	
}
