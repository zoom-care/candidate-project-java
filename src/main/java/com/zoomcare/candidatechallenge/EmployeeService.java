package com.zoomcare.candidatechallenge;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    public IEmployeeRepository repository;

    public EmployeeService(IEmployeeRepository repository) {
        this.repository = repository;
    }

    public Employee get(int id) throws Exception {
        return EmployeeMapper.map(repository.get(id));
    }

    public List<Employee> getBySupervisor(int supervisorId) throws Exception {
        return EmployeeMapper.map(repository.getBySupervisor(supervisorId));
    }

    /**
     * @return All employees in the database
     */
    public List<Employee> getAll() {
        // TODO Auto-generated method stub
        return null;
        /*final List<Employee> employees = new ArrayList<>();
        repository.getAll().forEach(employee -> employees.add(employee));
        return employees;*/
    }
}