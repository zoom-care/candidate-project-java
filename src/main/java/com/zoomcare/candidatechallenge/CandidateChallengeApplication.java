package com.zoomcare.candidatechallenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class CandidateChallengeApplication implements CommandLineRunner
{

    public static void main(String[] args)
    {
	SpringApplication.run(CandidateChallengeApplication.class, args);
    }

    @Autowired
    JdbcTemplate jdbcTemplate;
    
    @Override
    public void run(String... strings){
        System.out.println("!!!!!!!!!!!Initializing Application!!!!!!!!!!!!!!");

	jdbcTemplate.execute("DROP TABLE Employee IF EXISTS");
	jdbcTemplate.execute("CREATE TABLE Employee ( id BIGINT(19), supervisorId BIGINT(19))");
	jdbcTemplate.execute("INSERT INTO Employee VALUES (1, NULL)");
	jdbcTemplate.execute("INSERT INTO Employee VALUES (2, NULL)");
	jdbcTemplate.execute("INSERT INTO Employee VALUES (3,1)");
	jdbcTemplate.execute("INSERT INTO Employee VALUES (4,2)");

	jdbcTemplate.execute("DROP TABLE Properties IF EXISTS");
	jdbcTemplate.execute("CREATE TABLE Properties ( id BIGINT(19), key VARCHAR(256), value VARCHAR(256))");
	jdbcTemplate.execute("INSERT INTO Properties VALUES (1, 'Name', 'Kim Kimmy')");
	jdbcTemplate.execute("INSERT INTO Properties VALUES (1, 'Position', 'Supervisor A')");

	jdbcTemplate.execute("INSERT INTO Properties VALUES (2, 'Name', 'Jim Jimothy')");
	jdbcTemplate.execute("INSERT INTO Properties VALUES (2, 'Position', 'Supervisor B')");
	
	jdbcTemplate.execute("INSERT INTO Properties VALUES (3, 'Name', 'Joe Bean')");
	jdbcTemplate.execute("INSERT INTO Properties VALUES (3, 'Position', 'Underling A')");

	jdbcTemplate.execute("INSERT INTO Properties VALUES (4, 'Name', 'Suzy Sandals')");
	jdbcTemplate.execute("INSERT INTO Properties VALUES (4, 'Position', 'Underling B')");
	
	System.out.println("Finished initializing application");
    }
    
}
