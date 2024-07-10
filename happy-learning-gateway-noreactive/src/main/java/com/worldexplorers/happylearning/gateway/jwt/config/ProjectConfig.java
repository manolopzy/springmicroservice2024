package com.worldexplorers.happylearning.gateway.jwt.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.web.client.RestTemplate;

import com.worldexplorers.happylearning.gateway.jwt.authentication.provider.OtpAuthenticationProvider;
import com.worldexplorers.happylearning.gateway.jwt.authentication.provider.UsernamePasswordAuthenticationProvider;

@Configuration
public class ProjectConfig {
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
}
