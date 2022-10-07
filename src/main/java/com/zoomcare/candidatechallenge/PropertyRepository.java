package com.zoomcare.candidatechallenge;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyRepository extends JpaRepository<PropertyEntity, Long> {
}
