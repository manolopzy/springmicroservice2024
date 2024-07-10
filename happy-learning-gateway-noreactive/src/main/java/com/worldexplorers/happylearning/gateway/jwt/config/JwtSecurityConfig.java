package com.worldexplorers.happylearning.gateway.jwt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.worldexplorers.happylearning.gateway.jwt.authentication.filter.InitialAuthenticationFilter;
import com.worldexplorers.happylearning.gateway.jwt.authentication.filter.JwtAuthenticationFilter;
import com.worldexplorers.happylearning.gateway.jwt.authentication.provider.OtpAuthenticationProvider;
import com.worldexplorers.happylearning.gateway.jwt.authentication.provider.UsernamePasswordAuthenticationProvider;

@Configuration
@EnableWebSecurity
public class JwtSecurityConfig {

	@Autowired
	private InitialAuthenticationFilter initialAuthenticationFilter;
	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;

	@Autowired
	private OtpAuthenticationProvider otpAuthenticationProvider;

	@Autowired
	private UsernamePasswordAuthenticationProvider usernamePasswordAuthenticationProvider;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.authenticationProvider(otpAuthenticationProvider)
				.authenticationProvider(usernamePasswordAuthenticationProvider);

		http.addFilterAt(initialAuthenticationFilter, BasicAuthenticationFilter.class)
				.addFilterAfter(jwtAuthenticationFilter, BasicAuthenticationFilter.class);
		http.authorizeHttpRequests(c -> c.anyRequest().authenticated());

		return http.build();
	}

	@Bean
	public AuthenticationManager authenticationManager(HttpSecurity http) 
	  throws Exception {
	    return http.getSharedObject(AuthenticationManagerBuilder.class).build();
	}
}
