package com.worldexplorers.happylearning.gateway.jwt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ProjectConfig {
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	
	
//    public void addCorsMappings(CorsRegistry corsRegistry) {
//        corsRegistry.addMapping("/**")
//        
//        .allowedHeaders("*")
//          .allowedOrigins("http://localhost", "http://localhost:3000", "http://localhost:8084")
//          .allowedMethods("GET", "POST", "PUT", "DELETE")
//          .maxAge(3600L);
//    }
}
