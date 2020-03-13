package com.zoomcare.candidatechallenge;

import com.zoomcare.candidatechallenge.model.Employee;
import com.zoomcare.candidatechallenge.model.dto.EmployeeDTO;
import com.zoomcare.candidatechallenge.model.dto.PropertyDTO;
import com.zoomcare.candidatechallenge.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class EmployeeController
{
    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/employees/top-level")
    public ResponseEntity<List<EmployeeDTO>> findAllTopLevelEmployees() {
        final List<Employee> allTopLevelEmployees = employeeRepository.findAllTopLevelEmployees();
        final List<EmployeeDTO> employeeDTOs = allTopLevelEmployees.stream().map(employee -> {
            final EmployeeDTO employeeDTO = new EmployeeDTO();
            employeeDTO.setId(employee.getId());
            final List<EmployeeDTO> subordinateDTOs = convertToHierarchyEmployeeDTOs(employee);
            employeeDTO.setSubordinates(subordinateDTOs);
            final List<PropertyDTO> propertyDTOs = convertToPropertyDTOs(employee);
            employeeDTO.setProperties(propertyDTOs);
            return employeeDTO;
        }).collect(Collectors.toList());
        return ResponseEntity.ok(employeeDTOs);
    }

    private List<EmployeeDTO> convertToHierarchyEmployeeDTOs(Employee employee) {
        return employee.getEmployees().stream().map(subordinate -> {
            final EmployeeDTO subordinateDTO = new EmployeeDTO();
            subordinateDTO.setId(subordinate.getId());
            final List<PropertyDTO> propertyDTOs = convertToPropertyDTOs(subordinate);
            subordinateDTO.setProperties(propertyDTOs);
            return subordinateDTO;
        }).collect(Collectors.toList());
    }

    private List<PropertyDTO> convertToPropertyDTOs(Employee employee) {
        return employee.getProperties().stream().map(property -> {
            final PropertyDTO propertyDTO = new PropertyDTO();
            propertyDTO.setEmployeeId(property.getId().getEmployeeId());
            propertyDTO.setKey(property.getId().getKey());
            propertyDTO.setValue(property.getValue());
            return propertyDTO;
        }).collect(Collectors.toList());
    }
}
