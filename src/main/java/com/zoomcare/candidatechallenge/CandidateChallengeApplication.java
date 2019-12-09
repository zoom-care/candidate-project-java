package com.zoomcare.candidatechallenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaAuditing
@EntityScan("com.zoomcare")
@EnableJpaRepositories("com.zoomcare")
@ComponentScan("com.zoomcare")
public class CandidateChallengeApplication {

    public static void main(String[] args) {
        SpringApplication.run(CandidateChallengeApplication.class, args);
    }

}
