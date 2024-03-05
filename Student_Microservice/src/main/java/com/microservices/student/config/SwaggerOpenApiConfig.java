package com.microservices.student.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.microservices.student.constant.ApplicationConstant;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerOpenApiConfig {
	
	@Bean
    public OpenAPI usersMicroserviceOpenAPI() {
        return new OpenAPI()
                .info(new Info().title(ApplicationConstant.STUDENT_MICROSERVICE)
                                 .description(ApplicationConstant.STUDENT_MICROSERVICE_DESCRIPTION)
                                 .version("1.0"));
    }

}
