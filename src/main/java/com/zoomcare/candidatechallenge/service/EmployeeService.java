package com.zoomcare.candidatechallenge.service;

import com.zoomcare.candidatechallenge.dto.EmployeeDTO;
import com.zoomcare.candidatechallenge.dto.PropertiesDTO;
import com.zoomcare.candidatechallenge.exceptions.EmployeeNotFoundException;
import com.zoomcare.candidatechallenge.model.Employee;
import com.zoomcare.candidatechallenge.model.Properties;
import com.zoomcare.candidatechallenge.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<EmployeeDTO> getTopLevelEmployees() {
        List<Employee> topLevelEmployees = employeeRepository.findBySupervisorIsNull();
        return convertToDTO(topLevelEmployees);
    }

    public EmployeeDTO getEmployee(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            return convertToDTO(employee.get());
        } else {
            throw new EmployeeNotFoundException(id);
        }
    }

    private List<EmployeeDTO> convertToDTO(List<Employee> employees) {
        List<EmployeeDTO> employeeDTOs = new ArrayList<>();
        for (Employee employee : employees) {
            employeeDTOs.add(convertToDTO(employee));
        }
        return employeeDTOs;
    }

    public EmployeeDTO convertToDTO(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(employee.getId());
        employeeDTO.setProperties(convertPropertiesToDTO(employee.getProperties()));
        employeeDTO.setReports((List<EmployeeDTO>) convertToDTO(employee.getReports()));
        return employeeDTO;
    }

    public List<PropertiesDTO> convertPropertiesToDTO(List<Properties> properties) {
        List<PropertiesDTO> propertiesDTOs = new ArrayList<>();
        for (Properties property : properties) {
            propertiesDTOs.add(convertToDTO(property));
        }
        return propertiesDTOs;
    }

    private PropertiesDTO convertToDTO(Properties property) {
        PropertiesDTO propertiesDTO = new PropertiesDTO();
        propertiesDTO.setKey(property.getKey());
        propertiesDTO.setValue(property.getValue());
        return propertiesDTO;
    }
}
