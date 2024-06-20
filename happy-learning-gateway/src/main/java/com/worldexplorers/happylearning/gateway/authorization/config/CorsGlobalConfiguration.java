package com.worldexplorers.happylearning.gateway.authorization.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;

@Configuration
@EnableWebFlux
public class CorsGlobalConfiguration implements WebFluxConfigurer {

	@Override
    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//        .allowedOrigins("http://localhost:3000")
//        .allowedMethods("GET", "POST", "PUT", "DELETE");
    }
//    @Override
//    public void addCorsMappings(CorsRegistry corsRegistry) {
//        corsRegistry.addMapping("/**")
//          .allowedOrigins("http://localhost", "http://localhost:3000", "http://localhost:8084")
//          .allowedMethods("GET", "POST", "PUT", "DELETE")
//          .maxAge(3600L);
//    }
}
