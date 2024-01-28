package com.microservices.student.config;

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
 					.requestMatchers(HttpMethod.GET ,"/student/**").permitAll()
 					.requestMatchers(HttpMethod.POST ,"/student/**").permitAll()
 					.requestMatchers(HttpMethod.PUT ,"/student/**").permitAll()
 					.requestMatchers(HttpMethod.DELETE ,"/student/**").permitAll()
 					.requestMatchers("/actuator/**").hasRole("ADMIN")
 			).csrf().disable()
 			.formLogin();
 		return http.build();
 	}
}
