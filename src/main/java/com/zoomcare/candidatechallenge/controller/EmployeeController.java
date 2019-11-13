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
