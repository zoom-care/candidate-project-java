package com.zoomcare.candidatechallenge.services;

import com.zoomcare.candidatechallenge.dto.EmployeePropertiesDTO;
import com.zoomcare.candidatechallenge.dto.PropertiesDTO;
import com.zoomcare.candidatechallenge.exceptions.EmployeeNotFoundException;
import com.zoomcare.candidatechallenge.model.Employee;
import com.zoomcare.candidatechallenge.model.Property;
import com.zoomcare.candidatechallenge.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeePropertiesService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<EmployeePropertiesDTO> getTopEmployees(){
        List <EmployeePropertiesDTO> topEmployees = employeeRepository.getTopEmployees().stream().
                map(this::convertData).collect(Collectors.toList());

        return topEmployees;
    }

    public EmployeePropertiesDTO getEmployeeById(BigInteger id) {
        EmployeePropertiesDTO employee = convertData(employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException("Employee not found")));
        return employee;
    }

    private EmployeePropertiesDTO convertData(Employee employee){

        EmployeePropertiesDTO employeePropertiesDTO = new EmployeePropertiesDTO();
        employeePropertiesDTO.setId(employee.getId());
        employeePropertiesDTO.setSuperVisorId(employee.getSupervisor() == null ? null : employee.getSupervisor().getId());
        employeePropertiesDTO.setEmployees(employee.getEmployees().stream().map(this::convertData).collect(Collectors.toList()));
        employeePropertiesDTO.setProperties(employee.getProperties().stream().map(this::convertDataProperties).collect(Collectors.toList()));

        return employeePropertiesDTO;
    }

    private PropertiesDTO convertDataProperties(Property property) {

        PropertiesDTO propertiesDTO = new PropertiesDTO();
        propertiesDTO.setKey(property.getId().getKey());
        propertiesDTO.setValue(property.getValue());

        return propertiesDTO;
    }
}
