package com.zoomcare.candidatechallenge.service.impl;

import com.zoomcare.candidatechallenge.exceptions.NotFoundException;
import com.zoomcare.candidatechallenge.model.Employee;
import com.zoomcare.candidatechallenge.repository.EmployeeRepository;
import com.zoomcare.candidatechallenge.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);
    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> getAllEmployee() {
        LOGGER.debug("Retrieving all employees...");
        List<Employee> allEmployee = employeeRepository.findAll();
        return allEmployee;
    }

    @Override
    public Optional<Employee> getEmployeeById(Long employeeId) throws NotFoundException {
        LOGGER.debug("Retrieving employee by id : " + employeeId);
        try {
            Optional<Employee> employee = employeeRepository.findById(employeeId);

            return Optional.ofNullable(employee
                    .orElseThrow(() -> new NotFoundException("Employee not found for this id : " + employeeId)));
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<Employee> getSupervisorById(Integer employeeId) throws NotFoundException {
        LOGGER.debug("Retrieving supervisor by id : " + employeeId);

        try {
            List<Employee> supervisor = employeeRepository.findEmployeeBySupervisor(employeeId);
            if (supervisor.isEmpty()) {
                throw new NotFoundException("Supervisor not found for this id : " + employeeId);
            }
            return supervisor;
        } catch (Exception e) {
            throw e;
        }
    }
}
