package com.worldexplorers.happylearning.gateway.jwt.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;

import com.worldexplorers.happylearning.gateway.jwt.authentication.provider.OtpAuthenticationProvider;
import com.worldexplorers.happylearning.gateway.jwt.authentication.provider.UsernamePasswordAuthenticationProvider;

@Configuration
public class AuthenticationManagerConfig {

	@Autowired
	private OtpAuthenticationProvider otpAuthenticationProvider;

	@Autowired
	private UsernamePasswordAuthenticationProvider usernamePasswordAuthenticationProvider;
	
	@Bean
	public AuthenticationManager authenticationManager() {
		return new ProviderManager(List.of(otpAuthenticationProvider, usernamePasswordAuthenticationProvider));
	}
}
