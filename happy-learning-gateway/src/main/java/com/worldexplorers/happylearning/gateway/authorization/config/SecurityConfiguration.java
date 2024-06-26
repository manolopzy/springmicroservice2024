package com.worldexplorers.happylearning.gateway.authorization.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.util.matcher.PathPatternParserServerWebExchangeMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfiguration {


	    @Bean
	    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
	        http.httpBasic(Customizer.withDefaults());
	    	http.securityMatcher(new PathPatternParserServerWebExchangeMatcher("/**"))
	                .authorizeExchange(exchanges -> exchanges.anyExchange().authenticated());
	        return http.build();
	    }
		
	    @Bean
	    public MapReactiveUserDetailsService userDetailsService() {
	        UserDetails user = User.withUsername("username")
	                .password("password")
	                .roles("USER")
	                .build();
	        return new MapReactiveUserDetailsService(user);
	    }

//	    @Bean
//	    public CorsConfigurationSource corsConfiguration() {
//	        CorsConfiguration corsConfig = new CorsConfiguration();
//	        corsConfig.applyPermitDefaultValues();
//	        corsConfig.setAllowCredentials(true);
//	        corsConfig.addAllowedMethod("GET");
//	        corsConfig.addAllowedMethod("PATCH");
//	        corsConfig.addAllowedMethod("POST");
//	        corsConfig.addAllowedMethod("OPTIONS");
//	        corsConfig.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
//	        corsConfig.setAllowedHeaders(Arrays.asList("Authorization", "Requestor-Type"));
//	        corsConfig.setExposedHeaders(Arrays.asList("X-Get-Header"));
//	        UrlBasedCorsConfigurationSource source =
//	                new UrlBasedCorsConfigurationSource();
//	        source.registerCorsConfiguration("/**", corsConfig);
//	        return source;
//	    }

	    /**
	     * When {@link @EnableWebFluxSecurity} is used, the default cors configuration 
	     * will be overwritten by Spring webflux security
	     * @return
	     */
		@Bean
		public CorsConfigurationSource corsConfiguration() {
			CorsConfiguration corsConfig = new CorsConfiguration();
			corsConfig.applyPermitDefaultValues();
			corsConfig.setAllowCredentials(true);
//			corsConfig.addAllowedMethod("GET");
//			corsConfig.addAllowedMethod("DELETE");
//			corsConfig.addAllowedMethod("PATCH");
//			corsConfig.addAllowedMethod("POST");
//			corsConfig.addAllowedMethod("OPTIONS");
			//corsConfig.setAllowedMethods(Arrays.asList("POST"));
			corsConfig.setAllowedMethods(Arrays.asList("GET", "DELETE", "POST"));
			corsConfig.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
//			corsConfig.setAllowedHeaders(Arrays.asList("Authorization", "Requestor-Type"));
//			corsConfig.setExposedHeaders(Arrays.asList("X-Get-Header"));
			corsConfig.setAllowedHeaders(Arrays.asList("*"));
			corsConfig.setExposedHeaders(Arrays.asList("*"));
			UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
			source.registerCorsConfiguration("/**", corsConfig);
			return source;
		}

		
	    @Bean
	    public CorsWebFilter corsWebFilter() {
	        return new CorsWebFilter(corsConfiguration());
	    }
	    
	    @Bean
		PasswordEncoder encoder() {
			return new BCryptPasswordEncoder();
		}
}
