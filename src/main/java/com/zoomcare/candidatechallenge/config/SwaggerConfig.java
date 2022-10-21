package com.zoomcare.candidatechallenge.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	@Bean
	public Docket challengeApi() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).groupName("Candidate Challenge").select()
				.apis(RequestHandlerSelectors.basePackage("com.zoomcare.candidatechallenge.controllers")).build();

	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Challenge Api").description("The resulting services should allow users to get a list of all top-level employees or to specify an employee by id to return just that employee.")
				.contact(new Contact("Gabriel Leoni","","")).version("1.0").build();
	}
}
