package com.zoomcare.candidatechallenge.service;

/*
 * Interface for the employee service
 */

import java.util.List;

import com.zoomcare.candidatechallenge.model.EmployeeDTO;

public interface IServiceEmployee {

	public List<EmployeeDTO> findAll();
	public EmployeeDTO findById(Long id);
	public Long create(EmployeeDTO employee);
	public Long getById(Long id);
	public void update(EmployeeDTO employee);
	public void deleteById(Long id);
}
