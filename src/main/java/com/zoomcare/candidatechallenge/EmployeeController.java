package com.zoomcare.candidatechallenge;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    EmployeeService service;

    public EmployeeController() throws SQLException {
        try {
            EmployeeRepository repository = new EmployeeRepository();
            service = new EmployeeService(repository);
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
        log.info("Fetching employee ID " + id + " from the database.");
        try {
            Employee employee = service.get(id);
            employee.setDirectReports(service.getBySupervisor(id));
            return employee;
        } catch (Exception e) {
            throw (SQLException) e;
        }
    }

    /**
     * @return All employees in the database
     */
    @GetMapping(value = "/employee/getAll")
    public List<Employee> getAll() {
        log.info("Fetching all employees from the database.");
        return service.getAll();
    }
}