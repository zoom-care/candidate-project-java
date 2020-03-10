package com.zoomcare.candidatechallenge;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    private EmployeeService employeeService;
    private PropertyService propertyService;

    public EmployeeController() throws SQLException {
        try {
            initializeServices();
        } catch (SQLException e) {
            System.err.println("SQL error: " + e.getSQLState());
            throw e;
        }
    }

    /**
     * @return All employees in the database
     */
    @GetMapping(value = "/employee/get")
    public Employee get(int id) throws SQLException {
        System.out.println("Fetching employee ID " + id + " from the database.");
        try {
            Employee employee = employeeService.get(id);
            List<Employee> flat = employeeService.getBySupervisor(id);
            employee.setProperties(propertyService.getByEmployee(id));
            for (Employee directReport : flat) {
                employee.pushDirectReport(get(directReport.getId()));
            }
            return employee;
        } catch (Exception e) {
            throw (SQLException) e;
        }
    }

    /**
     * @return All employees in the database
     */
    @GetMapping(value = "/employee/getTopLevel")
    public List<Employee> getTopLevel() throws SQLException {
        System.out.println("Fetching all top-level employees from the database.");

        List<Employee> employees = new ArrayList<>();
        try {
            List<Employee> flat = employeeService.getBySupervisor(0);
            for (Employee employee : flat) {
                employees.add(get(employee.getId()));
            }
        } catch (Exception e) {
            throw (SQLException) e;
        }

        return employees;
    }

    /**
     * @return All employees in the database
     */
    @GetMapping(value = "/employee/getAll")
    public List<Employee> getAll() throws SQLException {
        System.out.println("Fetching all employees from the database.");
        try {
            return employeeService.getAll();
        } catch (Exception e) {
            throw (SQLException) e;
        }
    }

    private void initializeServices() throws SQLException {
        EmployeeRepository employeeRepository = new EmployeeRepository();
        employeeService = new EmployeeService(employeeRepository);
        PropertyRepository propertyRepository = new PropertyRepository();
        propertyService = new PropertyService(propertyRepository);
    }
}