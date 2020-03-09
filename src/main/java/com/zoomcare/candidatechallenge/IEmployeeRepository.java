package com.zoomcare.candidatechallenge;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface IEmployeeRepository {
    public EmployeeDO get(int id) throws Exception;
    public List<EmployeeDO> getBySupervisor(int supervisorId) throws Exception;
    public List<EmployeeDO> getAll() throws Exception;
}