package com.zoomcare.candidatechallenge.daos;

import com.zoomcare.candidatechallenge.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Specifications for the mechanism for finding Employee entities.
 *
 * @author Allen Wickham
 */
@Repository
public interface IEmployeeDao extends JpaRepository<Employee, Long>
{
}
