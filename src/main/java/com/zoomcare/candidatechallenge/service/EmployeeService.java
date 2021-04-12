package com.zoomcare.candidatechallenge.service;

import com.zoomcare.candidatechallenge.dto.EmployeeDTO;
import com.zoomcare.candidatechallenge.dto.EmployeePropertyDTO;
import com.zoomcare.candidatechallenge.model.Employee;
import com.zoomcare.candidatechallenge.model.Property;
import com.zoomcare.candidatechallenge.repository.EmployeeRepository;
import com.zoomcare.candidatechallenge.repository.PropertyRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final PropertyRepository propertyRepository;

    public EmployeeService(EmployeeRepository employeeRepository, PropertyRepository propertyRepository) {
        this.employeeRepository = employeeRepository;
        this.propertyRepository = propertyRepository;
    }

    public List<EmployeeDTO> getTopLevelEmployees() {
        return employeeRepository
                .findEmployeesBySupervisorIdIsNull()
                .stream()
                .map(this::convertToEmployeeDTO)
                .collect(Collectors.toList());
    }

    public EmployeeDTO getEmployeeById(BigInteger employeeId) throws EntityNotFoundException {
        Employee employee = employeeRepository.findEmployeesById(employeeId);
        if (employee == null) {
            throw new EntityNotFoundException("No employee found with id " + employeeId + ".");
        }
        return convertToEmployeeDTO(employee);
    }

    private EmployeeDTO convertToEmployeeDTO(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(employee.getId());
        employeeDTO.setSupervisorId(employee.getSupervisorId());
        employeeDTO.setProperties(employee.getProperties().stream().map(this::convertToEmployeePropertyDTO).collect(Collectors.toList()));
        employeeDTO.setDirectReports(employeeRepository.findEmployeesBySupervisorId(employee.getId()).stream().map(this::convertToEmployeeDTO).collect(Collectors.toList()));

        return employeeDTO;
    }

    private EmployeePropertyDTO convertToEmployeePropertyDTO(Property property) {
        EmployeePropertyDTO employeePropertyDTO = new EmployeePropertyDTO();
        employeePropertyDTO.setKey(property.getKey());
        employeePropertyDTO.setValue(property.getValue());
        return employeePropertyDTO;
    }

}


