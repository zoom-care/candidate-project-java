package com.zoomcare.candidatechallenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.zoomcare.candidatechallenge"})
@EnableJpaRepositories(basePackages = {"com.zoomcare.candidatechallenge.repository"})
@EntityScan(basePackages = {"com.zoomcare.candidatechallenge.model"})
public class CandidateChallengeApplication
{

	public static void main(String[] args)
	{
		SpringApplication.run(CandidateChallengeApplication.class, args);
	}

}
