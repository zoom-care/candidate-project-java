package com.zoomcare.candidatechallenge;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EmployeeService service;

    /**
     * Saves an employee to the database
     * 
     * @param employee
     * @return ID of the newly saved Employee
     */
    @PostMapping(value = "/employee/save")
    public int save(final @RequestBody @Valid Employee employee) {
        log.info("Saving employee to the database.");
        System.out.println("Saving employee to the database.");
        service.save(employee);
        return employee.getId();
    }

    /**
     * @return All employees in the database
     */
    @GetMapping(value = "/employee/getAll", produces = "application/vnd.jcg.api.v1+json")
    public List<Employee> getAll() {
        log.info("Fetching all employees from the database.");
        System.out.println("Fetching all employees from the database.");
        return service.getAll();
    }
}