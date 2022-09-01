package com.zoomcare.candidatechallenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class CandidateChallengeApplication
{

	public static void main(String[] args)
	{
		SpringApplication.run(CandidateChallengeApplication.class, args);
	}

}

/*
Do want:
service for employees - interface?
repository layer for accessing db - interfaces
models? or json?
tests
 */

/*
need:
get all top-level employees
get an employee by id

all employees returned include id, all properties, and a nested list of direct reports
 */