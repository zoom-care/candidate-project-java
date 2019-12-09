package com.zoomcare.candidatechallenge.service;

import com.zoomcare.candidatechallenge.model.Employee;
import com.zoomcare.candidatechallenge.persistence.EmployeeRepository;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    /**
     * Return an employee by their database id
     *
     * @param id the id of the Employee
     * @return an {@Link Employee} if found, null if not
     */
    public Employee getEmployee(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    /**
     * Retrieve a list of all employees who do not have a supervisor (top-level employees)
     *
     * @return a list of {@link Employee} objects that do not reference a supervisor
     */
    public List<Employee> getTopLevelList() {
        return employeeRepository.findEmployeesBySupervisorIdIsNull();
    }
}
