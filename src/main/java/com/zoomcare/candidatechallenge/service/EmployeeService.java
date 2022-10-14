package com.zoomcare.candidatechallenge.service;

import com.zoomcare.candidatechallenge.model.Employee;
import com.zoomcare.candidatechallenge.model.EmployeeWithReports;
import com.zoomcare.candidatechallenge.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeService {

    EmployeeRepository employeeRepository;

    @GetMapping("/{id}")
    public EmployeeWithReports getById(@PathVariable Long id) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        if (employee == null) {
            return null;
        }
        EmployeeWithReports result = new EmployeeWithReports();
        result.setId(employee.getId());
        result.setSupervisorId(employee.getSupervisorId());
        result.setProperties(employee.getProperties());
        result.setReports(collectReportsFor(employee.getId()));
        return result;
    }

    @GetMapping("/all")
    public List<EmployeeWithReports> getAll() {
        List<EmployeeWithReports> result = new ArrayList<>();
        List<Employee> topLevel = employeeRepository.findBySupervisorIdIsNull();
        topLevel.forEach(employee -> {
            EmployeeWithReports employeeWithReports = new EmployeeWithReports();
            employeeWithReports.setId(employee.getId());
            employeeWithReports.setSupervisorId(employee.getSupervisorId());
            employeeWithReports.setProperties(employee.getProperties());
            employeeWithReports.setReports(collectReportsFor(employee.getId()));
            result.add(employeeWithReports);
        });
        return result;
    }


    private List<EmployeeWithReports> collectReportsFor(Long id) {
        List<EmployeeWithReports> result = new ArrayList<>();
        List<Employee> reports = employeeRepository.findBySupervisorId(id);
        reports.forEach(employee -> {
            EmployeeWithReports employeeWithReports = new EmployeeWithReports();
            employeeWithReports.setId(employee.getId());
            employeeWithReports.setSupervisorId(employee.getSupervisorId());
            employeeWithReports.setProperties(employee.getProperties());
            employeeWithReports.setReports(collectReportsFor(employee.getId()));
            result.add(employeeWithReports);
        });
        return result;
    }
}
