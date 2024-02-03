package com.microservices.student.config;

import org.springframework.context.annotation.Configuration;

@Configuration
//@EnableWebSecurity
public class ActuatorSecurityConfig {
	
//	@Bean
// 	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
// 		http
// 			.authorizeHttpRequests((authorizeHttpRequests) ->
// 				authorizeHttpRequests
// 					.requestMatchers(HttpMethod.GET ,"/student/**").permitAll()
// 					.requestMatchers(HttpMethod.POST ,"/student/**").permitAll()
// 					.requestMatchers(HttpMethod.PUT ,"/student/**").permitAll()
// 					.requestMatchers(HttpMethod.DELETE ,"/student/**").permitAll()
// 					.requestMatchers("/actuator/**").hasRole("ADMIN")
// 			).csrf().disable()
// 			.formLogin();
// 		http.exceptionHandling().disable();
// 		return http.build();
// 	}
}
