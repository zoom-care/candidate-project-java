package com.zoomcare.candidatechallenge.service;

import com.zoomcare.candidatechallenge.model.Employee;
import com.zoomcare.candidatechallenge.model.EmployeeWithReports;
import com.zoomcare.candidatechallenge.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeWithReports getById(@PathVariable Long id) {
        if (!employeeRepository.existsById(id)) {
            return null;
        }
        return employeeRepository
                .findById(id)
                .map(this::getEmployeeWithReports)
                .orElse(null);
    }

    public List<EmployeeWithReports> getAll() {
        return employeeRepository
                .findBySupervisorIdIsNull()
                .stream()
                .map(this::getEmployeeWithReports)
                .collect(Collectors.toList());
    }

    /**
     * Recursively collects reports via findBySupervisorId.
     *
     * @param id the id of the superior employee.
     * @return a list of reports.
     */
    private List<EmployeeWithReports> collectReportsFor(Long id) {
        return employeeRepository
                .findBySupervisorId(id)
                .stream()
                .map(this::getEmployeeWithReports)
                .collect(Collectors.toList());
    }

    /**
     * Creates a new EmployeeWithReports object.
     *
     * @param employee the DAU instance to copy.
     * @return an EmployeeWithReports object.
     */
    private EmployeeWithReports getEmployeeWithReports(Employee employee) {
        EmployeeWithReports result = new EmployeeWithReports();
        result.setId(employee.getId());
        result.setSupervisorId(employee.getSupervisorId());
        result.setProperties(employee.getProperties());
        result.setReports(collectReportsFor(employee.getId()));
        return result;
    }
}
