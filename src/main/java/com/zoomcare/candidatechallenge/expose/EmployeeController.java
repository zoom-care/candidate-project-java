package com.zoomcare.candidatechallenge.expose;

import com.zoomcare.candidatechallenge.business.EmployeeService;
import com.zoomcare.candidatechallenge.business.exception.DataException;
import com.zoomcare.candidatechallenge.db.model.Employee;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.util.List;

@RestController
@RequestMapping("/v1/employees")
@Slf4j
@RequiredArgsConstructor
@Validated
public class EmployeeController {

  private final EmployeeService employeeService;

  @GetMapping(name = "find all employees",
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(value = HttpStatus.OK)
  public List<Employee> findAllEmployees() {
    try {
      return employeeService.findAllEmployees();
    } catch (DataException e) {
      throw  new ResponseStatusException(HttpStatus.NOT_FOUND);
    } catch (Exception e) {
      throw  new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping(name = "find employee by ID", value = "{employeeId}",
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(value = HttpStatus.OK)
  public Employee findEmployeeById(
      @Pattern(regexp = "^[0-9]+$", message = "employeeId should be a number")
      @Valid @PathVariable String employeeId) {
    try {
      return employeeService.findByEmployeeId(employeeId);
    } catch (DataException e) {
      throw  new ResponseStatusException(HttpStatus.NOT_FOUND);
    } catch (Exception e) {
      throw  new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}
