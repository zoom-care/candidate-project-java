package com.zoomcare.candidatechallenge.lib;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.zoomcare.candidatechallenge.dto.EmployeeDto;
import com.zoomcare.candidatechallenge.dto.PropertyDto;
import com.zoomcare.candidatechallenge.model.Employee;
import com.zoomcare.candidatechallenge.model.Property;

public interface EmployeeMapStruct {
	
	EmployeeMapStruct MAPSTRUCT_INSTANCE = Mappers.getMapper(EmployeeMapStruct.class);
	EmployeeDto employeeToDtoEmployee(Employee employee);
	
	@Mapping(source="id.key", target="key")
	PropertyDto propertytoDtoProperty(Property property);
	
	
}
