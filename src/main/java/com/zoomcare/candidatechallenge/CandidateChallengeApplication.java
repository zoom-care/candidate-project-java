package com.zoomcare.candidatechallenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class CandidateChallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(CandidateChallengeApplication.class, args);
	}

}
