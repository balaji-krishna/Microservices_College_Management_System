package com.microservices.student.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
 				{
					try {
						authorizeHttpRequests
							.requestMatchers("/student/**").hasRole("REQUESTER").anyRequest().authenticated()
							.requestMatchers("/actuator/**").hasRole("ADMIN").anyRequest().authenticated();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
 			).httpBasic().and().csrf().disable()
 			.formLogin();
 		return http.build();
 	}
}
