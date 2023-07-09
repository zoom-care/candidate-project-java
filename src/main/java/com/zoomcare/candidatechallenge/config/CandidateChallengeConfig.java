package com.zoomcare.candidatechallenge.config;

import com.zoomcare.candidatechallenge.repository.EmployeeRepository;
import com.zoomcare.candidatechallenge.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CandidateChallengeConfig {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public EmployeeService employeeService(ModelMapper modelMapper, EmployeeRepository employeeRepository) {
        return new EmployeeService(employeeRepository, modelMapper);
    }
}
