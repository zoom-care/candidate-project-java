package com.zoomcare.candidatechallenge.endpoint;
import com.zoomcare.candidatechallenge.model.Employee;
import com.zoomcare.candidatechallenge.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeeEndpoint {

    private static final Integer DEFAULT_PAGE_SIZE = 100;

    private EmployeeService employeeService;

    public EmployeeEndpoint(@Autowired EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(value = "/{employeeId}")
    public ResponseEntity<Employee> getEmployeeInfo(@PathVariable("employeeId") Long employeeId) {
        Optional<Employee> employee = employeeService.getEmployeeById(employeeId);
        if (employee.isPresent()) {
            return new ResponseEntity<Employee>(employee.get(), HttpStatus.OK);
        } else return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getTopEmployees(
            @RequestHeader(defaultValue = "0") Integer page,
            @RequestHeader(defaultValue = "10") Integer size) {

        Pageable paging = PageRequest.of(page,
                size > DEFAULT_PAGE_SIZE ?
                        DEFAULT_PAGE_SIZE : size);

        return new ResponseEntity<>(employeeService.getTopEmployee(paging), HttpStatus.OK);
    }


}