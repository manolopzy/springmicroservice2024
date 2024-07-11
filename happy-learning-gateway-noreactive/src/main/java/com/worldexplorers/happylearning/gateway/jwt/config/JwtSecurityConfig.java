package com.worldexplorers.happylearning.gateway.jwt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.worldexplorers.happylearning.gateway.jwt.authentication.filter.InitialAuthenticationFilter;
import com.worldexplorers.happylearning.gateway.jwt.authentication.filter.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class JwtSecurityConfig {

	@Autowired
	private InitialAuthenticationFilter initialAuthenticationFilter;
	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;

	
	@Autowired
	private AuthenticationManager authenticationManager;
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

//		http.authenticationProvider(otpAuthenticationProvider)
//				.authenticationProvider(usernamePasswordAuthenticationProvider);

		http.csrf(c->c.disable());
		http.authenticationManager(authenticationManager);

		http.addFilterAt(initialAuthenticationFilter, BasicAuthenticationFilter.class)
				.addFilterAfter(jwtAuthenticationFilter, BasicAuthenticationFilter.class);
		http.authorizeHttpRequests(c -> c.anyRequest().authenticated());

		return http.build();
	}

	
}
