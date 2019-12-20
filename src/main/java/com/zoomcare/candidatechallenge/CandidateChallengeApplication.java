package com.zoomcare.candidatechallenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class CandidateChallengeApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(CandidateChallengeApplication.class, args);
	}
}
