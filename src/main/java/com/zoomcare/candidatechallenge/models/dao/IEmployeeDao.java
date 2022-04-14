package com.zoomcare.candidatechallenge.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.zoomcare.candidatechallenge.models.entity.Employee;


public interface IEmployeeDao extends CrudRepository<Employee, Long>{

}
