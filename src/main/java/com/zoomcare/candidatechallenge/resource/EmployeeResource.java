package com.zoomcare.candidatechallenge.resource;

import java.util.List;

import javax.validation.constraints.NotBlank;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.zoomcare.candidatechallenge.dto.EmployeeInfoDTO;
import com.zoomcare.candidatechallenge.dto.TopLevelListDTO;
import com.zoomcare.candidatechallenge.exception.EmployeeException;
import com.zoomcare.candidatechallenge.service.EmployeeService;

/**
 * Employee APIs
 */
@RestController
@RequestMapping("/v1")
public class EmployeeResource {
	
	private static final Logger LOG = LoggerFactory.getLogger(EmployeeResource.class);

	private final EmployeeService employeeService;
	
	@Autowired
	public EmployeeResource(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	/**
	 * Return employee information
	 * @param id - Employee ID
	 * @return Employee information
	 */
	@GetMapping(value = "/employee/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EmployeeInfoDTO> getEmployeeInformation(@PathVariable("id") @NotBlank Long id) {
		
		LOG.info(String.format("Get employee information for employee ID: %d", id));
		try {
			EmployeeInfoDTO employee = employeeService.getEmployeeInformation(id);
			LOG.info(String.format("Employee found: %s", employee.toString()));
			return ResponseEntity.status(HttpStatus.OK).body(employee);
		
		} catch (EmployeeException e) {
			LOG.error(e.getMessage());
			return ResponseEntity.status(e.getHttpStatus()).body(new EmployeeInfoDTO(e.getMessage()));
		}
	}
	
	/**
	 * Get all employees
	 * @return Employee list
	 */
	@GetMapping(value = "/employee/all", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TopLevelListDTO>> getAllEmployees() {
		List<TopLevelListDTO> employeeList = employeeService.getAllEmployees();
		return ResponseEntity.status(HttpStatus.OK).body(employeeList);
	}
	

}
