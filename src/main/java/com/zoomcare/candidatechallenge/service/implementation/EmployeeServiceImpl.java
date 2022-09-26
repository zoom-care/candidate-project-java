package com.zoomcare.candidatechallenge.service.implementation;


import com.zoomcare.candidatechallenge.exception.BusinessException;
import com.zoomcare.candidatechallenge.exception.DataNotFoundException;
import com.zoomcare.candidatechallenge.exception.UnexpectedException;
import com.zoomcare.candidatechallenge.model.entity.Employee;
import com.zoomcare.candidatechallenge.repository.EmployeeRepository;
import com.zoomcare.candidatechallenge.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getAllTopLevelEmployees() throws BusinessException {
        try {

            final List<Employee> allTopLevelEmployees = employeeRepository.getAllTopLevelEmployees();

            if (allTopLevelEmployees == null || allTopLevelEmployees.isEmpty()) {
                throw new DataNotFoundException("Top-level employees not found");
            }

            return allTopLevelEmployees;

        } catch (Exception exception) {
            if (exception instanceof BusinessException) {
                throw exception;
            }

            final String errorMessage = "Something went wrong trying to get all top-level employees";

            log.error(errorMessage, exception);

            throw new UnexpectedException(errorMessage, exception);
        }
    }

    @Override
    public Employee findById(Long id) throws BusinessException {

        try {

            final Employee employee = employeeRepository.findById(id).orElse(null);

            if (employee == null) {
                throw new DataNotFoundException("Employee with id " + id + " does not exist");
            }

            return employee;

        } catch (Exception exception) {
            if (exception instanceof BusinessException) {
                throw exception;
            }

            final String errorMessage = "Something went wrong trying to get the employee with id " + id;

            log.error(errorMessage, exception);

            throw new UnexpectedException(errorMessage, exception);
        }
    }
}
