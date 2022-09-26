package com.zoomcare.candidatechallenge.controller;

import com.zoomcare.candidatechallenge.model.dto.CommonResponse;
import com.zoomcare.candidatechallenge.model.dto.EmployeeDto;
import com.zoomcare.candidatechallenge.model.entity.Employee;
import com.zoomcare.candidatechallenge.model.enums.ResponseCode;
import com.zoomcare.candidatechallenge.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("employees")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/top-level")
    @Operation(summary = "Get the nested organization structure starting from the top level employees")
    public ResponseEntity<CommonResponse> getAllTopLevelEmployees() {
        final List<Employee> employees = employeeService.getAllTopLevelEmployees();

        final List<EmployeeDto> result = employees.stream().map(e -> employeeToDto(e)).collect(Collectors.toList());

        return new ResponseEntity<>(
                new CommonResponse(
                        ResponseCode.OPERATION_SUCCESSFUL, "Nested organization structure", result
                ), HttpStatus.OK
        );
    }

    @GetMapping("/{employeeId}")
    @Operation(summary = "Get the nested organization structure starting from the specified employee")
    public ResponseEntity<CommonResponse> getEmployeeById(@PathVariable("employeeId") Long employeeId) {
        final Employee employee = employeeService.findById(employeeId);

        final EmployeeDto result = employeeToDto(employee);

        return new ResponseEntity<>(
                new CommonResponse(
                        ResponseCode.OPERATION_SUCCESSFUL, "Found employee with id " + employeeId, result
                ), HttpStatus.OK
        );
    }


    private EmployeeDto employeeToDto(Employee employee) {
        if (employee == null) {
            return null;
        }

        final EmployeeDto employeeDto = new EmployeeDto();

        final Employee supervisor = employee.getSupervisor();
        final Long supervisorId;
        if (supervisor != null) {
            supervisorId = supervisor.getId();
        } else {
            supervisorId = null;
        }

        employeeDto.setSupervisorId(supervisorId);
        employeeDto.setEmployeeId(employee.getId());
        employeeDto.setProperties(employee.getProperties());

        final List<Employee> directReports = employee.getDirectReports();
        final List<EmployeeDto> directReportsDtos;

        if (directReports != null && !directReports.isEmpty()) {
            directReportsDtos = directReports.stream().map(dr -> employeeToDto(dr)).collect(Collectors.toList());
        } else {
            directReportsDtos = null;
        }

        employeeDto.setDirectReports(directReportsDtos);

        return employeeDto;
    }
}
