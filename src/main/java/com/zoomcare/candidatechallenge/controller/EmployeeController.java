package com.zoomcare.candidatechallenge.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.zoomcare.candidatechallenge.model.Employee;
import com.zoomcare.candidatechallenge.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "EmployeeController")
@RestController
@RequestMapping("/v1/employee")
@Slf4j
public class EmployeeController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @ApiOperation(value = "Retrieve Employee by ID", response = Employee.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 404, message = "not found!!!")})
    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> getById(@PathVariable Long id) {
        log.debug("looking up employee {}", id);
        Employee employee = employeeService.getEmployee(id);
        if (employee == null) {
            log.warn("Failed to find employee for id {}", id);
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @ApiOperation(value = "Retrieve Top Level employees", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 404, message = "not found!!!")})
    @GetMapping(value = "/toplevel", produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> getToplevelEmployees() {
        log.debug("Retrieving list of top level employees");
        List<Employee> topLevelList = employeeService.getTopLevelList();
        if (topLevelList == null || topLevelList.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(topLevelList, HttpStatus.OK);
    }

}
