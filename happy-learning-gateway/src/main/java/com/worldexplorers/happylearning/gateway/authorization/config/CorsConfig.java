package com.worldexplorers.happylearning.gateway.authorization.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

/**
 * https://www.baeldung.com/spring-webflux-cors
 */
@Configuration
public class CorsConfig {
	
	@Bean
	public CorsWebFilter corsWebFilter() {
		CorsConfiguration corsConfig = new CorsConfiguration().applyPermitDefaultValues();
      
      UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
      source.registerCorsConfiguration("/**", corsConfig);

      return new CorsWebFilter(source);
	    
	}
	
//	@Bean
//    public CorsWebFilter corsWebFilter() {
//        CorsConfiguration corsConfig = new CorsConfiguration();
//        corsConfig.setAllowedOrigins(Arrays.asList("http://localhost", "http://localhost:3000", "http://localhost:8084"));
//        corsConfig.setAllowCredentials(true);
//        corsConfig.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
//        corsConfig.setAllowedHeaders(Arrays.asList("*"));
//        corsConfig.setMaxAge(3600L);
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", corsConfig);
//
//        return new CorsWebFilter(source);
//    }
}
