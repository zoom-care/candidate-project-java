package com.zoomcare.candidatechallenge.employee.business;

import com.zoomcare.candidatechallenge.employee.dataaccess.EmployeeDAO;
import com.zoomcare.candidatechallenge.employee.dataaccess.IEmployeeRepository;
import com.zoomcare.candidatechallenge.employee.dataaccess.IPropertyRepository;
import com.zoomcare.candidatechallenge.employee.dataaccess.PropertyDAO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class EmployeeService {

    private final IEmployeeRepository employeeRepository;
    private final IPropertyRepository propertyRepository;

    public EmployeeService(IEmployeeRepository employeeRepository, IPropertyRepository propertyRepository) {
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
                .findById(id)
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
