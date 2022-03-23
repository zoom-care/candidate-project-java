package com.zoomcare.candidatechallenge.adapters;

import com.zoomcare.candidatechallenge.model.Employee;
import com.zoomcare.candidatechallenge.model.Property;
import com.zoomcare.candidatechallenge.model.dto.EmployeeDto;
import com.zoomcare.candidatechallenge.model.dto.PropertyDto;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class EmployeeAdapter {

    public static EmployeeDto map(Employee employee) {
        EmployeeDto dto = new EmployeeDto();
        dto.setId(employee.getId());
        dto.setSupervisorId(employee.getSupervisor() != null ? employee.getSupervisor().getId() : null);

        List<EmployeeDto> employees = employee.getEmployees().stream().map(employee1 -> EmployeeAdapter.map(employee1)).collect(Collectors.toList());
        dto.setEmployees(employees);

        List<PropertyDto> properties = employee.getProperties().stream().map(property -> PropertyAdapter.map(property)).collect(Collectors.toList());
        dto.setProperties(properties);

        return dto;
    }

    public static List<EmployeeDto> map(List<Employee> employees) {
        List<EmployeeDto> employeesList = employees.stream().map(employee -> {
            EmployeeDto dto = new EmployeeDto();
            dto.setId(employee.getId());
            dto.setProperties(PropertyAdapter.map(employee.getProperties()));
            return dto;
        }).collect(Collectors.toList());
        return employeesList;
    }
}
