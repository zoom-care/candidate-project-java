package com.zoomcare.candidatechallenge.controller;

import com.example.zoomcarecodechallenge.dto.EmployeeDTO;
import com.example.zoomcarecodechallenge.dto.PropertiesDTO;
import com.example.zoomcarecodechallenge.exceptions.EmployeeNotFoundException;
import com.example.zoomcarecodechallenge.model.Employee;
import com.example.zoomcarecodechallenge.model.Properties;
import com.example.zoomcarecodechallenge.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeRepository employeeRepository;


    @GetMapping("/")
    public List<EmployeeDTO> getTopLevelEmployees() {
        List<Employee> topLevelEmployees = employeeRepository.findBySupervisorIsNull();
        return convertToDTO(topLevelEmployees);
    }

    @GetMapping("/{id}")
    public EmployeeDTO getEmployee(@PathVariable Long id) {
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

    private EmployeeDTO convertToDTO(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(employee.getId());
        employeeDTO.setProperties(convertPropertiesToDTO(employee.getProperties()));
        employeeDTO.setReports((List<EmployeeDTO>) convertToDTO(employee.getReports()));
        return employeeDTO;
    }

    private List<PropertiesDTO> convertPropertiesToDTO(List<Properties> properties) {
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
    @ExceptionHandler(EmployeeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Exception handleEmployeeNotFoundException(EmployeeNotFoundException ex) {
        return new Exception("Employee not found", ex);
    }
}