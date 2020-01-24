package com.zoomcare.candidatechallenge.service;

import com.zoomcare.candidatechallenge.dao.EmployeeDao;
import com.zoomcare.candidatechallenge.dao.PropertyDao;
import com.zoomcare.candidatechallenge.model.Employee;
import com.zoomcare.candidatechallenge.model.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeService {
    private final EmployeeDao employeeDao;
    private final PropertyDao propertyDao;

    @Autowired
    public EmployeeService(@Qualifier("H2EmployeeDao") EmployeeDao employeeDao,
                           @Qualifier("H2PropertyDao") PropertyDao propertyDao) {
        this.employeeDao = employeeDao;
        this.propertyDao = propertyDao;
    }

    public List<Employee> getAllEmployees() {
        HashSet<Long> visited = new HashSet();
        List<Employee> employees = employeeDao.getAllEmployees();
        for (Employee employee : employees) {
            visited.clear();
            visited.add(employee.getId());
            loadProperties(employee);
            loadDirectReports(employee, visited);
        }
        return employees;
    }

    public Optional<Employee> getEmployeeById(Long id) {
        Optional<Employee> employeeMaybe = employeeDao.getEmployeeById(id);
        if (employeeMaybe.isPresent()) {
            Employee employee = employeeMaybe.get();
            loadProperties(employee);
            HashSet<Long> visited = new HashSet();
            visited.add(employee.getId());
            loadDirectReports(employee, visited);
        }
        return employeeMaybe;
    }

    protected Employee loadProperties(Employee employee) {
        if (employee != null) {
            List<Property> properties = propertyDao.getPropertyByEmployeeId(employee.getId());
            employee.setProperties(properties);
        }
        return employee;
    }

/*
    Recursively load direct reports of the employee. It does not include the same employee in the tree twice
    to avoid infinite loop.
*/
    protected void loadDirectReports(Employee employee, HashSet<Long> visited) {
        List<Employee> reports = employeeDao.getEmployeesBySupervisorId(employee.getId());
        List<Employee> reportsNoLoop = new ArrayList();
        for (Employee report : reports) {
            // avoid loop
            if (!visited.contains(report.getId())) {
                visited.add(report.getId());
                loadProperties(report);
                loadDirectReports(report, visited);
                reportsNoLoop.add(report);
            }
        }
        employee.setDirectReports(reportsNoLoop);
    }
}
