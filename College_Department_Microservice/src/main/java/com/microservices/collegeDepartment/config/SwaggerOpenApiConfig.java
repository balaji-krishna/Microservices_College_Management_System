package com.microservices.collegeDepartment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerOpenApiConfig {
	
	@Bean
    public OpenAPI usersMicroserviceOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("College Department Microservice")
                                 .description("Spring boot application for College Department Microservice")
                                 .version("1.0"));
    }

}
