package com.zoomcare.candidatechallenge.service;

import com.zoomcare.candidatechallenge.model.Employee;
import com.zoomcare.candidatechallenge.model.EmployeeResponse;
import com.zoomcare.candidatechallenge.model.Property;
import com.zoomcare.candidatechallenge.repository.EmployeeRepository;
import com.zoomcare.candidatechallenge.repository.PropertyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    EmployeeRepository employeeRepository;
    PropertyRepository propertyRepository;

    @Override
    public EmployeeResponse getEmployee(Long id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        List<Property> propertyByEmployeeId = propertyRepository.findAllPropertyByEmployeeId(id);
        Employee employee = employeeOptional.isPresent() ? employeeOptional.get() : new Employee();
        return EmployeeResponse.builder()
                .id(employee.getId())
                .supervisorId(employee.getSupervisorId())
                .propertyList(propertyByEmployeeId)
                .build();
    }

    @Override
    public List<EmployeeResponse> getEmployees() {
        List<Employee> employeeList = employeeRepository.findAll();
        List<Property> propertyList = propertyRepository.findAll();
        return employeeList.stream().map(employee -> EmployeeResponse.builder()
                .id(employee.getId())
                .supervisorId(employee.getSupervisorId())
                .propertyList(propertyList.stream().filter(property ->
                        property.getEmployeeId() == employee.getId()).collect(Collectors.toList()))
                .build()).collect(Collectors.toList());
    }
}
