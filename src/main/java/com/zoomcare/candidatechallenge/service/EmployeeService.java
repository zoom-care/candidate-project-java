package com.zoomcare.candidatechallenge.service;

import com.zoomcare.candidatechallenge.NotFoundException;
import com.zoomcare.candidatechallenge.controller.dto.EmployeeResponseDTO;
import com.zoomcare.candidatechallenge.controller.dto.PropertyResponseDTO;
import com.zoomcare.candidatechallenge.data.entity.Employee;
import com.zoomcare.candidatechallenge.data.repository.EmployeeRepository;
import com.zoomcare.candidatechallenge.mapper.EmployeeToEmployeeResponseMapper;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Collection<EmployeeResponseDTO> getAll() {
        return StreamSupport.stream(employeeRepository.findAll().spliterator(), false).map(
                EmployeeToEmployeeResponseMapper::map).collect(Collectors.toList()) ;
    }

    public EmployeeResponseDTO getById(long id) throws NotFoundException {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new NotFoundException("Employee not found"));

        EmployeeResponseDTO employeeResponse = new EmployeeResponseDTO(employee.getId());
        List<PropertyResponseDTO> properties = getProperties(employee);

        employeeResponse.setProperties(properties);

        List<EmployeeResponseDTO> subordinateList = employee.getSubordinates().stream()
                .map(EmployeeToEmployeeResponseMapper::map)
                .collect(Collectors.toList());

        employeeResponse.setDirectSubordinates(subordinateList);
        return employeeResponse;
    }

    private List<PropertyResponseDTO> getProperties(Employee employee) {
        return employee.getProperties().stream()
                .map(p -> new PropertyResponseDTO(p.getKey(), p.getValue()))
                .collect(Collectors.toList());
    }
}
