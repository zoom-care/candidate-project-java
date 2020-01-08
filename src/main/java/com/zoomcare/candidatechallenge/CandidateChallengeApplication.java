package com.zoomcare.candidatechallenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Application : Use Spring JPA for access database 
// Two Methodes to create restful webservices 
// 1 - Use Spring Data Rest : localhost:8080/api
// 2 - Use Restcontroller  : localhost:8080/employees/{id}

@SpringBootApplication
public class CandidateChallengeApplication
{

	public static void main(String[] args)
	{
		SpringApplication.run(CandidateChallengeApplication.class, args);
	}

}
