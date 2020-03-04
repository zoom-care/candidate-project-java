package com.zoomcare.candidatechallenge.repository;

import com.zoomcare.candidatechallenge.model.Properties;
import org.springframework.data.repository.CrudRepository;
import java.math.BigInteger;

public interface PropertiesRepsitory extends CrudRepository<Properties, BigInteger> {
}
