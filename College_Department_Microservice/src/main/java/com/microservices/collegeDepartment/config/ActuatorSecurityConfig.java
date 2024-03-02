package com.microservices.collegeDepartment.config;

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
 					.requestMatchers(HttpMethod.GET ,"/department/**").permitAll()
					.requestMatchers(HttpMethod.POST ,"/department/**").permitAll()
					.requestMatchers(HttpMethod.PUT ,"/department/**").permitAll()
					.requestMatchers(HttpMethod.DELETE ,"/department/**").permitAll()
					.requestMatchers(HttpMethod.GET ,"/teacher/**").permitAll()
					.requestMatchers(HttpMethod.POST ,"/teacher/**").permitAll()
					.requestMatchers(HttpMethod.PUT ,"/teacher/**").permitAll()
					.requestMatchers(HttpMethod.DELETE ,"/teacher/**").permitAll()
 					.requestMatchers("/actuator/**").hasRole("ADMIN")
 			).csrf().disable()
 			.formLogin();
 		return http.build();
 	}
}
