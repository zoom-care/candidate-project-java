package com.zoomcare.candidatechallenge.mapper;

import com.zoomcare.candidatechallenge.dto.EmployeeDto;
import com.zoomcare.candidatechallenge.dto.EmployeePropertyDto;
import com.zoomcare.candidatechallenge.model.Employee;
import com.zoomcare.candidatechallenge.model.EmployeeProperty;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EmployeeMapper {

    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);
    EmployeeDto employeeToEmployeeDto(Employee employee);

    @Mapping(source="id.key", target="key")
    EmployeePropertyDto employeePropertyToEmployeePropertyDto(EmployeeProperty employeeProperty);
}
