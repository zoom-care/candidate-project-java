package com.zoomcare.candidatechallenge.repository;

import com.zoomcare.candidatechallenge.model.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.QueryHint;
import java.util.List;

import static org.hibernate.jpa.QueryHints.HINT_PASS_DISTINCT_THROUGH;

public interface EmployeeRepository extends CrudRepository<Employee, Long>
{
    List<Employee> findAllBySupervisorIsNull();

    @Transactional(readOnly = true)
    @Query("SELECT distinct e FROM Employee e " +
        "LEFT JOIN FETCH e.employees WHERE e.supervisor IS NULL ")
    @QueryHints(value = { @QueryHint(name = HINT_PASS_DISTINCT_THROUGH, value = "false") })
    List<Employee> fetchAllTopSupervisors();

    @Transactional(readOnly = true)
    @Query("SELECT distinct e FROM Employee e " +
        "LEFT JOIN FETCH e.properties WHERE e in :top ")
    @QueryHints(value = { @QueryHint(name = HINT_PASS_DISTINCT_THROUGH, value = "false") })
    List<Employee> fetchAllTopWithProperties(@Param("top") List<Employee> top);
}
