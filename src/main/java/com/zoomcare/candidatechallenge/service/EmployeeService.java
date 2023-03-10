package com.zoomcare.candidatechallenge.service;

import java.util.List;

import com.zoomcare.candidatechallenge.bean.EmployeeBean;

public interface EmployeeService {

	EmployeeBean findById(Long id);
	List<EmployeeBean> findTopLevelEmployee();
	
}
