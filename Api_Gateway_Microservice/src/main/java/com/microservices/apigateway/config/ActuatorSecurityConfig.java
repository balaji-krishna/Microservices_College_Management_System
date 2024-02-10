package com.microservices.apigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class ActuatorSecurityConfig {
	
	@Bean
 	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
 		http
 			.authorizeHttpRequests((authorizeHttpRequests) ->
 				authorizeHttpRequests
 				.requestMatchers(HttpMethod.GET ,"/**").permitAll()
				.requestMatchers(HttpMethod.POST ,"/**").permitAll()
				.requestMatchers(HttpMethod.PUT ,"/**").permitAll()
				.requestMatchers(HttpMethod.DELETE ,"/**").permitAll()
 					.requestMatchers("/actuator/**").hasRole("ADMIN")
 			)
 			.formLogin();
 		return http.build();
 	}
}
