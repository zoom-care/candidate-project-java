package com.zoomcare.candidatechallenge.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zoomcare.candidatechallenge.entity.Employee;
import com.zoomcare.candidatechallenge.exception.EmployeeErrorResponse;
import com.zoomcare.candidatechallenge.exception.EmployeeNotFoundException;
import com.zoomcare.candidatechallenge.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

	final Logger logger = LogManager.getLogger(EmployeeRestController.class.getSimpleName());

	@Autowired
	private EmployeeService employeeService;

	@RequestMapping("/employees")
	public ResponseEntity<List<Employee>> getTopLevelEmployees() {
		List<Employee> employeeList = employeeService.getTopLevelEmployees();
		return new ResponseEntity<>(employeeList, HttpStatus.OK);
	}

	@RequestMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
		Employee employee = employeeService.getEmployeeById(id);
		return new ResponseEntity<>(employee, HttpStatus.OK);
	}

	// TODO: Move the exception handlers in a centralized class (e.g
	// ControllerApiException) annotated with @ControllerAdvice
	// This allows multiple controllers to handle REST exceptions, instead of
	// duplicating these handlers below in each controller

	@ExceptionHandler
	public ResponseEntity<EmployeeErrorResponse> handleEmployeeNotFoundException(EmployeeNotFoundException enfe) {

		EmployeeErrorResponse error = new EmployeeErrorResponse()
				.setStatus(HttpStatus.NOT_FOUND.value())
				.setMessage(enfe.getMessage())
				.setTimeStamp(System.currentTimeMillis());

		logger.error("handleEmployeeNotFoundException:", enfe);

		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler
	public ResponseEntity<EmployeeErrorResponse> handleGenericException(Exception ex) {

		EmployeeErrorResponse error = new EmployeeErrorResponse()
				.setStatus(HttpStatus.BAD_REQUEST.value())
				.setMessage(ex.getMessage())
				.setTimeStamp(System.currentTimeMillis());

		logger.error("handleGenericException:", ex);

		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
}
