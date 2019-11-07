package com.zoomcare.candidatechallenge.controller;

import com.zoomcare.candidatechallenge.model.dto.EmployeePropertyDto;
import com.zoomcare.candidatechallenge.service.impl.EmployeeServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// The database is a relational database containing two tables, EMPLOYEE and PROPERTIES. The
// EMPLOYEE table is a simple mapping of an employee's id to their supervisor's id (or null if
// they have no supervisor). The PROPERTIES table contains a map of key/value pairs of properties
// for the employee.
//
// The resulting services should allow users to get a list of all top-level employees or to
// specify an employee by id to return just that employee. The result for each employee, whether
// in the top level list or an individual, should include the employee's Id and all properties as
// well as a nested list of all direct reports for that employee. This applies any where in the
// structure an employee is displayed, creating a deeply nested structure of the organization.

/**
 * This class is the main Employee controller.
 */
@RestController
@RequestMapping(EmployeeController.RESOURCE_PATH)
public class EmployeeController {
  static final String RESOURCE_PATH = "/employee";

  private final EmployeeServiceImpl employeeService;

  @Autowired
  public EmployeeController(final EmployeeServiceImpl employeeService) {
    this.employeeService = employeeService;
  }

  @GetMapping(value = "/{employeeId}")
  public ResponseEntity getEmployeeById(@PathVariable(value = "employeeId") final Long employeeId) {
    List<EmployeePropertyDto> employeeList = employeeService.getEmployeeById(employeeId);
    return new ResponseEntity<List<EmployeePropertyDto>>(employeeList, HttpStatus.OK);
  }

  @GetMapping()
  public ResponseEntity<List<EmployeePropertyDto>> getTopLevelEmployees() {
    List<EmployeePropertyDto> employeeList = employeeService.getTopLevelEmployees();
    return new ResponseEntity<List<EmployeePropertyDto>>(employeeList, HttpStatus.OK);
  }
}
