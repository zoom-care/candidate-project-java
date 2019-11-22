package com.zoomcare.candidatechallenge.repository;

import com.zoomcare.candidatechallenge.entity.EmployeeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends CrudRepository<EmployeeEntity,Long> {
    List<EmployeeEntity> findAll();
    EmployeeEntity findEmployeeById(Long id);
}
