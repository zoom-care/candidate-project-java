package com.zoomcare.candidatechallenge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.net.URL;
import java.sql.Connection;

@SpringBootApplication
public class CandidateChallengeApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(CandidateChallengeApplication.class, args);
	}

}
