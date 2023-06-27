package com.zoomcare.candidatechallenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.company.model.PropertiesEntity;

public interface PropertyRepository  extends JpaRepository<PropertyEntity, Long> {

}
