package com.zoomcare.candidatechallenge.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zoomcare.candidatechallenge.bean.EmployeeBean;
import com.zoomcare.candidatechallenge.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService service;

	@GetMapping("/topLevel")
	public List<EmployeeBean> getTopLevelEmployees() {
		return this.service.findTopLevelEmployee();
	}

	@GetMapping("{id}")
	public EmployeeBean getEmployeeById(@PathVariable Long id) {
		return this.service.findById(id);
	}
}