package com.zoomcare.candidatechallenge.consumer;

import com.zoomcare.candidatechallenge.dto.EmployeeDto;
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

@RestController
@RequestMapping("/employee")
public class EmployeeConsumer {

    private static final Integer DEFAULT_PAGE_SIZE = 10;

    private final EmployeeService employeeService;

    public EmployeeConsumer(@Autowired EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(value = "/{employeeId}")
    public ResponseEntity<EmployeeDto> getEmployeeInfo(@PathVariable("employeeId") Long employeeId) {
        return new ResponseEntity<>(employeeService.getEmployeeById(employeeId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getTopEmployees(
            @RequestHeader(defaultValue = "0") Integer page,
            @RequestHeader(defaultValue = "100") Integer size) {

        Pageable paging = PageRequest.of(page,
                size > DEFAULT_PAGE_SIZE ?
                        DEFAULT_PAGE_SIZE : size);

        return new ResponseEntity<>(employeeService.getTopEmployee(paging), HttpStatus.OK);
    }
}
