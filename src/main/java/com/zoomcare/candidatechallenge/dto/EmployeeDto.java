package com.zoomcare.candidatechallenge.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class EmployeeDto {
	
	private Long id;
		
	private List<PropertyDto> properties = new ArrayList<>();
}
