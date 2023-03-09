package com.zoomcare.candidatechallenge.service;

import com.zoomcare.candidatechallenge.dto.EmployeeDTO;
import com.zoomcare.candidatechallenge.dto.PropertyDTO;
import com.zoomcare.candidatechallenge.model.Employee;
import com.zoomcare.candidatechallenge.model.Property;
import com.zoomcare.candidatechallenge.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Handles organization operations
 */
@Service
public class OrganizationService {

    private EmployeeRepository employeeRepository;

    public OrganizationService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Optional<EmployeeDTO> findById(Long id) {
        return employeeRepository.findById(id).map(this::employeeToEmployeeDTO);
    }

    public List<EmployeeDTO> getAllTopLevelsEmployees(){
        return employeeRepository.findAllBySupervisorIdIsNull().stream()
                .map(this::employeeToEmployeeDTO)
                .collect(Collectors.toList());
    }

    private EmployeeDTO employeeToEmployeeDTO(Employee employee){
        EmployeeDTO dto = new EmployeeDTO();
        dto.setId(employee.getId());
        dto.setSupervisorId(employee.getSupervisorId());
        if(!CollectionUtils.isEmpty(employee.getEmployees())){
            dto.setEmployees(employee.getEmployees().stream().map(this::employeeToEmployeeDTO).collect(Collectors.toList()));
        } else {
            dto.setEmployees(Collections.emptyList());
        }
        if(!CollectionUtils.isEmpty(employee.getProperties())){
            dto.setProperties(employee.getProperties().stream().map(this::mapProperty).collect(Collectors.toList()));
        } else {
            dto.setProperties(Collections.emptyList());
        }
        return dto;
    }
    private PropertyDTO mapProperty(Property property){
        return new PropertyDTO(property.getId().getKey(), property.getValue());
    }
}
