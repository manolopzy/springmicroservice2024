package com.worldexplorers.happylearning.gateway.jwt.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

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
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("http://localhost", "http://localhost:3000", "http://localhost:8084"));
		//configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
		
//		configuration.setAllowedOrigins(Arrays.asList("*"));
	    configuration.setAllowedMethods(Arrays.asList("*"));
	    configuration.setAllowedHeaders(Arrays.asList("*"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

//		http.authenticationProvider(otpAuthenticationProvider)
//				.authenticationProvider(usernamePasswordAuthenticationProvider);

		http.csrf(c->c.disable());
		http.cors(c->c.configurationSource(corsConfigurationSource()));
		http.authenticationManager(authenticationManager);

		http.addFilterAt(initialAuthenticationFilter, BasicAuthenticationFilter.class)
				.addFilterAfter(jwtAuthenticationFilter, BasicAuthenticationFilter.class);
		http.authorizeHttpRequests(c -> c.anyRequest().authenticated());

		return http.build();
	}

	
}
