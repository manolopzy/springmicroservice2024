package com.worldexplorers.happylearning.gateway.jwt.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Spring Framework provides first class support for CORS. CORS must be processed before Spring Security because the pre-flight request will not contain any cookies (i.e. the JSESSIONID). If the request does not contain any cookies and Spring Security is first, the request will determine the user is not authenticated (since there are no cookies in the request) and reject it.
 */
@Configuration
public class CorsGlobalConfiguration{// implements WebMvcConfigurer {
    
//    @Override
//    public void addCorsMappings(CorsRegistry corsRegistry) {
//        corsRegistry.addMapping("/**")
//        
//        .allowedHeaders("*")
//          .allowedOrigins("http://localhost", "http://localhost:3000", "http://localhost:8084")
//          .allowedMethods("GET", "POST", "PUT", "DELETE")
//          .maxAge(3600L);
//    }
}
