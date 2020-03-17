package com.zoomcare.candidatechallenge.employee.business;

import com.zoomcare.candidatechallenge.employee.dataaccess.EmployeeDAO;
import com.zoomcare.candidatechallenge.employee.dataaccess.EmployeeRepository;
import com.zoomcare.candidatechallenge.employee.dataaccess.PropertyRepository;
import com.zoomcare.candidatechallenge.employee.dataaccess.PropertyDAO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final PropertyRepository propertyRepository;

    public EmployeeService(EmployeeRepository employeeRepository, PropertyRepository propertyRepository) {
        this.employeeRepository = employeeRepository;
        this.propertyRepository = propertyRepository;
    }

    public List<Employee> getEmployees() {
        return employeeRepository
                .getAllBySupervisorIdNull()
                .stream()
                .map(this::convertEmployeeDAOToEmployee)
                .collect(Collectors.toList());
    }

    public Optional<Employee> getEmployee(Long id) {
        return employeeRepository
                .getById(id)
                .map(this::convertEmployeeDAOToEmployee);
    }

    private Employee convertEmployeeDAOToEmployee(EmployeeDAO employeeDAO) {
        return Employee
                .builder()
                .id(employeeDAO.getId())
                .supervisorId(employeeDAO.getSupervisorId())
                .directReports(getDirectReports(employeeDAO.getId()))
                .properties(getProperties(employeeDAO.getId()))
                .build();
    }

    private List<Employee> getDirectReports(Long supervisorId) {
        return employeeRepository
                .getAllBySupervisorId(supervisorId)
                .stream()
                .map(this::convertEmployeeDAOToEmployee)
                .collect(Collectors.toList());
    }

    private List<Property> getProperties(Long employeeId) {
        return propertyRepository
                .getAllByEmployeeId(employeeId)
                .stream()
                .map(this::convertPropertyDAOToProperty)
                .collect(Collectors.toList());
    }

    private Property convertPropertyDAOToProperty(PropertyDAO propertyDAO) {
        return Property
                .builder()
                .employeeId(propertyDAO.employeeId)
                .key(propertyDAO.key)
                .value(propertyDAO.value)
                .build();
    }

}
