package com.zoomcare.candidatechallenge.repository;

import com.zoomcare.candidatechallenge.model.Employee;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class EmployeeRepositoryImpl {
    @PersistenceContext
    EntityManager entityManager;

    public List<Employee> findAll() {
        return entityManager.createNativeQuery("SELECT * FROM EMPLOYEE", Employee.class).getResultList();
    }

    public Employee getEmployeesByID(Long employeeId) {
        Query q = entityManager.createNativeQuery("SELECT * FROM EMPLOYEE as a INNER JOIN PROPERTIES as b ON a.ID = b.EMPLOYEE_ID WHERE ID = :employeeId", Employee.class);
        q.setParameter("employeeId", employeeId);
        List<Employee> employees = q.getResultList();
        return employees.size() == 1 ? employees.get(0) : null;
    }
}
