package com.zoomcare.candidatechallenge.repository;

import com.zoomcare.candidatechallenge.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface PropertiesRepository extends JpaRepository<Property, Long> {
    List<Property> findByEmployeeId(Long id);

    @Transactional
    void deleteByEmployeeId(long id);
}
