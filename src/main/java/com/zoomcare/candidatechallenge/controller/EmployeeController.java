package com.zoomcare.candidatechallenge.controller;

import com.zoomcare.candidatechallenge.data.dto.EmployeeDTO;
import com.zoomcare.candidatechallenge.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {
  
  private final EmployeeService employeeService;
  
  @GetMapping("/top-level")
  public List<EmployeeDTO> getTopLevelEmployees() {
    log.info("/employees/top-level request");
    return employeeService.getTopLevelEmployees();
  }
  
  @GetMapping("/{id}")
  public EmployeeDTO getEmployeeById(@PathVariable("id") final long employeeId) {
    log.info("/employees/{} request", employeeId);
    return employeeService.getEmployee(employeeId);
  }
}
