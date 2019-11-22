package com.zoomcare.candidatechallenge.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Slf4j
@Configuration
public class DatabaseConfig {

    @Bean
    public DataSource getDataSource(@Value("${database.url}") String url, @Value("${database.username}") String username) {
        log.info("Setting up db connection with url {}", url);
        log.debug("db connection username {}", username);
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.url(url);
        dataSourceBuilder.username(username);
        return dataSourceBuilder.build();
    }
}
