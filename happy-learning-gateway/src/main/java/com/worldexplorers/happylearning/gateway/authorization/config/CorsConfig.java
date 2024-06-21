package com.worldexplorers.happylearning.gateway.authorization.config;

import org.springframework.context.annotation.Configuration;

/**
 * https://www.baeldung.com/spring-webflux-cors
 */
@Configuration
//@EnableWebFluxSecurity
public class CorsConfig {
	
//	@Bean
//    public MapReactiveUserDetailsService userDetailsService() {
//        UserDetails user = User.withDefaultPasswordEncoder()
//                .username(basicAuth.getUsername())
//                .password(basicAuth.getPassword())
//                .roles("USER")
//                .build();
//        return new MapReactiveUserDetailsService(user);
//    }

//    @Bean
//    public CorsConfigurationSource corsConfiguration() {
//        CorsConfiguration corsConfig = new CorsConfiguration();
//        corsConfig.applyPermitDefaultValues();
//        //corsConfig.setAllowCredentials(true);
//        corsConfig.addAllowedMethod("GET");
//        corsConfig.addAllowedMethod("PATCH");
//        corsConfig.addAllowedMethod("POST");
//        corsConfig.addAllowedMethod("OPTIONS");
//        corsConfig.addAllowedMethod("DELETE");
//        //corsConfig.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
//        corsConfig.setAllowedOrigins(Arrays.asList("*"));
//        corsConfig.setAllowedHeaders(Arrays.asList("*"));
//        corsConfig.setExposedHeaders(Arrays.asList("*"));
//        UrlBasedCorsConfigurationSource source =
//                new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", corsConfig);
//        return source;
//    }
//
//    @Bean
//    public CorsWebFilter corsWebFilter() {
//        return new CorsWebFilter(corsConfiguration());
//    }
//	@Bean
//	public CorsWebFilter corsWebFilter() {
//		CorsConfiguration corsConfig = new CorsConfiguration().applyPermitDefaultValues();
//      
//      UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//      source.registerCorsConfiguration("/**", corsConfig);
//
//      return new CorsWebFilter(source);
//	    
//	}
	
//	@Bean
//    public CorsWebFilter corsWebFilter() {
//        CorsConfiguration corsConfig = new CorsConfiguration();
//        corsConfig.setAllowedOrigins(Arrays.asList("http://localhost", "http://localhost:3000", "http://localhost:8089"));
//        //corsConfig.setAllowCredentials(true);
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
