package com.zoomcare.candidatechallenge.service.impl;

import com.zoomcare.candidatechallenge.entity.Employee;
import com.zoomcare.candidatechallenge.repository.EmployeeRepository;
import com.zoomcare.candidatechallenge.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService
{

    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(final EmployeeRepository employeeRepository)
    {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee create(Employee employee)
    {
        validateEmployeeSupervisor(employee);
        resetProperties(employee);
        employeeRepository.save(employee);
        return employee;
    }

    /**
     * Method to reset the properties
     * @param employee      Employee object
     */
    private void resetProperties(Employee employee)
    {
        if (Objects.nonNull(employee.getProperties())) {
            employee.getProperties().stream().forEach(data -> {
                data.setEmployee(employee);
            });
        }
    }

    /**
     * Method to validate if the supervisor_id is valid or invalid
     * @param employee      Employee object
     */
    private void validateEmployeeSupervisor(Employee employee)
    {
        if (Objects.nonNull(employee.getSupervisorId()))
        {
            Optional<Employee> optionalEmployee = employeeRepository.findById(employee.getSupervisorId());
            if (!optionalEmployee.isPresent())
            {
                throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
            }
        }
    }

    @Override
    public Employee getById(final Long id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if (!employeeOptional.isPresent()) {
            throw new HttpClientErrorException(HttpStatus.NO_CONTENT);
        }
        return employeeOptional.get();
    }

    @Override
    public List<Employee> getEmployees() {
        List<Employee> employeeList = employeeRepository.findAll();
        if (Objects.isNull(employeeList) || employeeList.isEmpty()) {
            throw new HttpClientErrorException(HttpStatus.NO_CONTENT);
        }
        return employeeList;
    }

    @Override
    public void deleteById(final Long id) {
        getById(id);
        employeeRepository.deleteById(id);
    }
}
