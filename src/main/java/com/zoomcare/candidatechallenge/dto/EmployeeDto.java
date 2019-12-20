package com.zoomcare.candidatechallenge.dto;

import java.util.Map;
import java.util.Set;

public class EmployeeDto {
	private long id;
	private Map<String, String> properties;
	private Set<EmployeeDto> directorReport;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Map<String, String> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, String> properties) {
		this.properties = properties;
	}

	public Set<EmployeeDto> getDirectorReport() {
		return directorReport;
	}

	public void setDirectorReport(Set<EmployeeDto> directorReport) {
		this.directorReport = directorReport;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmployeeDto other = (EmployeeDto) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}
