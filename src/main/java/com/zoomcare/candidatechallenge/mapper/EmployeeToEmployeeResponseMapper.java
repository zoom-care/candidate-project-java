package com.zoomcare.candidatechallenge.mapper;

import com.zoomcare.candidatechallenge.controller.dto.EmployeeResponseDTO;
import com.zoomcare.candidatechallenge.controller.dto.PropertyResponseDTO;
import com.zoomcare.candidatechallenge.data.entity.Employee;
import java.util.List;
import java.util.stream.Collectors;

public final class EmployeeToEmployeeResponseMapper {

    private EmployeeToEmployeeResponseMapper() {
    }

    public static EmployeeResponseDTO map(Employee employee) {
        EmployeeResponseDTO employeeResponse = new EmployeeResponseDTO(employee.getId());
        List<PropertyResponseDTO>
                properties = employee.getProperties().stream().map(p -> new PropertyResponseDTO(p.getKey(), p.getValue()))
                .collect(Collectors.toList());
        employeeResponse.setProperties(properties);
        return employeeResponse;

    }
}
