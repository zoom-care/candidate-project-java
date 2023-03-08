package com.zoomcare.candidatechallenge.mapper;

import com.zoomcare.candidatechallenge.dto.EmployeeDTO;
import com.zoomcare.candidatechallenge.model.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface EmployeeMapper {

    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    EmployeeDTO employeeToEmployeeDTO(Employee employee);

    List<EmployeeDTO> map(List<Employee> employees);

}
