package com.zoomcare.candidatechallenge.employee.dataaccess;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IPropertyRepository extends CrudRepository<PropertyDAO, String> {
    public List<PropertyDAO> getAllByEmployeeId(Long employeeId);
}
