package com.zoomcare.candidatechallenge.employee.dataaccess;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IEmployeeRepository extends CrudRepository<EmployeeDAO, Long> {
    List<EmployeeDAO> getAllBySupervisorId(Long supervisorId);

    List<EmployeeDAO> getAllBySupervisorIdNull();
}
