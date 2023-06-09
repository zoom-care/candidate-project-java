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
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    EmployeeRepository employeeRepository;
    PropertyRepository propertyRepository;

    @Override
    public Optional<EmployeeResponse> getEmployee(Long id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if (employeeOptional.isPresent()) {
            List<Property> propertyByEmployeeId = propertyRepository.findAllPropertyByEmployeeId(id);
            Employee employee = employeeOptional.get();
            return Optional.of(EmployeeResponse.builder()
                    .id(employee.getId())
                    .supervisorId(employee.getSupervisorId())
                    .propertyList(propertyByEmployeeId)
                    .reporterList(employeeRepository.findAllBySupervisorId(employee.getId()))
                    .build());
        }
        return Optional.empty();
    }

    @Override
    public List<EmployeeResponse> getEmployees() {
        Set<Long> supervisors = employeeRepository.findAll().stream()
                .filter(e -> e.getSupervisorId() != null)
                .map(e -> e.getSupervisorId()).collect(Collectors.toSet());
        List<Employee> employeeList = employeeRepository.findAllById(supervisors);
        List<Property> propertyList = propertyRepository.findAll();
        return employeeList.stream().map(employee -> EmployeeResponse.builder()
                .id(employee.getId())
                .supervisorId(employee.getSupervisorId())
                .propertyList(propertyList.stream()
                        .filter(property -> property.getEmployeeId() == employee.getId())
                        .collect(Collectors.toList()))
                .reporterList(employeeRepository.findAllBySupervisorId(employee.getId()))
                .build())
                .collect(Collectors.toList());
    }
}
