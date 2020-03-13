package com.zoomcare.candidatechallenge.repository;

import com.zoomcare.candidatechallenge.model.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.QueryHint;
import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Long>
{
    @Query("SELECT distinct e FROM Employee e " +
            "LEFT JOIN FETCH e.employees LEFT JOIN FETCH e.properties WHERE e.supervisor IS NULL ")
    @QueryHints(value = { @QueryHint(name = "hibernate.query.passDistinctThrough", value = "false") })
    List<Employee> findAllTopLevelEmployees();
}
