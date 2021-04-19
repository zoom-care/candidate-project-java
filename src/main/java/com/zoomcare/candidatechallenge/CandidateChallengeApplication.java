package com.zoomcare.candidatechallenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import java.sql.*;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class CandidateChallengeApplication
{

	@RequestMapping(value="/welcome")
	public static String Welcome() {
        return "Welcome to Spring Boot \n";
    }
	static Connection conn;
	public static void main(String[] args)
	{
		String DB_URL = "jdbc:h2:mem:testdb";
		String USER = "sa";
		String PASS = "";
		try{
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
		} catch (Exception e){
			System.out.println("Exception: " + e);
		}
		SpringApplication.run(CandidateChallengeApplication.class, args);
	}

}
