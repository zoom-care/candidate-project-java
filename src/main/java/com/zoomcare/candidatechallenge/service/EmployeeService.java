package com.zoomcare.candidatechallenge.service;

import com.zoomcare.candidatechallenge.dto.EmployeeDTO;
import com.zoomcare.candidatechallenge.dto.EmployeePropertyDTO;
import com.zoomcare.candidatechallenge.model.Employee;
import com.zoomcare.candidatechallenge.model.Property;
import com.zoomcare.candidatechallenge.repository.EmployeeRepository;
import com.zoomcare.candidatechallenge.repository.PropertyRepository;
import org.springframework.stereotype.Service;

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
//        this.employeeService = employeeService;
    }

    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepository
                .findAll()
                .stream()
                .map(this::convertToEmployeeDTO)
                .collect(Collectors.toList());
    }

    private EmployeeDTO convertToEmployeeDTO(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(employee.getId());
        employeeDTO.setSupervisorId(employee.getSupervisorID());
        employeeDTO.setProperties(employee.getProperties().stream().map(this::convertToEmployeePropertyDTO).collect(Collectors.toList()));
        employeeDTO.setDirectReports(employeeRepository.findEmployeesBysupervisorID(employee.getId()).stream().map(this::convertToEmployeeDTO).collect(Collectors.toList()));
//        List<EmployeePropertyDTO> employeePropertyDTOS = getAllEmployeeProperties(employee.getID());
//        employeeDTO.setDirectReports();

//        Location location = user.getLocation();
//        userLocationDTO.setLat(location.getLat());
//        userLocationDTO.setLng(location.getLng());
//        userLocationDTO.setPlace(location.getPlace());
        return employeeDTO;
    }

//    public List<EmployeePropertyDTO> getAllEmployeeProperties(BigInteger employeeID) {
//        return propertyRepository
//                .findPropertiesByEMPLOYEEID(employeeID)
//                .stream()
//                .map(this::convertToEmployeePropertyDTO)
//                .collect(Collectors.toList());
//    }

    private EmployeePropertyDTO convertToEmployeePropertyDTO(Property property) {
        EmployeePropertyDTO employeePropertyDTO = new EmployeePropertyDTO();
        employeePropertyDTO.setKey(property.getKey());
        employeePropertyDTO.setValue(property.getValue());
        return employeePropertyDTO;
    }
}


