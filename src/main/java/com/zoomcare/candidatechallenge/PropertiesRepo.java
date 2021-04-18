package com.zoomcare.candidatechallenge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.zoomcare.candidatechallenge.Properties;
import java.math.BigInteger;

public interface PropertiesRepo extends JpaRepository<Properties, BigInteger>{ 

}  