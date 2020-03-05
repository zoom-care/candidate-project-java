package com.zoomcare.candidatechallenge.repository;

import com.zoomcare.candidatechallenge.model.db.Properties;
import org.springframework.data.repository.CrudRepository;
import java.math.BigInteger;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PropertiesRepository extends CrudRepository<Properties, BigInteger> {

    @Query(value = "SELECT * FROM PROPERTY WHERE EMPLOYEE_ID = ?1", nativeQuery = true)
    List<Properties> getAllPropertiesByEmployeeId(@Param("id") BigInteger id);

    @Query(value = "SELECT * FROM PROPERTY WHERE VALUE LIKE '%CEO%' Or VALUE LIKE '%President%'", nativeQuery = true)
    List<Properties> getTopLevelEmployeeProperties();

}
