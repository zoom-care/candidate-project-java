package com.zoomcare.candidatechallenge.repository;

import com.zoomcare.candidatechallenge.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface PropertyRepository extends JpaRepository<Property, Long> {
}