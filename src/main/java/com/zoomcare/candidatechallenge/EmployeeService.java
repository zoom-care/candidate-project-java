package com.zoomcare.candidatechallenge;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    public IEmployeeRepository repository;

    public EmployeeService(IEmployeeRepository repository) {
        this.repository = repository;
    }

    /**
     * @return Employee with the given ID
     */
    public Employee get(int id) throws Exception {
        return EmployeeMapper.map(repository.get(id));
    }

    /**
     * @return All employees who report to the given supervisor
     */
    public List<Employee> getBySupervisor(int supervisorId) throws Exception {
        return EmployeeMapper.map(repository.getBySupervisor(supervisorId));
    }

    /**
     * @return All employees in the database
     */
    public List<Employee> getAll() throws Exception {
        return EmployeeMapper.map(repository.getAll());
    }
}