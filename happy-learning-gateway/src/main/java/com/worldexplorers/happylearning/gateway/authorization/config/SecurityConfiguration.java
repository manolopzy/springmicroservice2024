package com.worldexplorers.happylearning.gateway.authorization.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
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

	/**
	 * We use {@link new PathPatternParserServerWebExchangeMatcher("/**")} to specify 
	 * the patterns of requests that are dealt with by this filter chain.
	 * In this case, all types of requests will be dealt by this filter chain
	 * 
	 * We can define various filter chains, each one of them is in charge of 
	 * some specific examinations
	 * 
	 * @param http
	 * @return
	 */
	@Bean
	public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
		/**
		 * http basic provides us an authentication filter which using an 
		 * authentication manager that delegates the responsibility to an 
		 * authentication provider that could have different authentication logic 
		 * implementations
		 */
		http.httpBasic(Customizer.withDefaults());
		/**
		 * new PathPatternParserServerWebExchangeMatcher("/**")
		 * Applying to all path 
		 */
		http.securityMatcher(new PathPatternParserServerWebExchangeMatcher("/**"))
				.authorizeExchange((exchanges) -> exchanges
//	                		.anyExchange()
//	                        .authenticated()
						// any URL that starts with /admin/ requires the role "ROLE_ADMIN"
						.pathMatchers("/admin/**").hasRole("ADMIN")
//						.pathMatchers("/arithmetic/**").authenticated()
//						.pathMatchers("/gamification/**").authenticated()
						.pathMatchers("/arithmetic/**").permitAll()
						.pathMatchers("/gamification/**").permitAll()
						// a POST to /users requires the role "USER_POST"
//	                        .pathMatchers(HttpMethod.POST, "/users").hasAuthority("USER_POST")
//	                        // a request to /users/{username} requires the current authentication's username
//	                        // to be equal to the {username}
//	                        .pathMatchers("/users/{username}").access((authentication, context) ->
//	                            authentication
//	                                .map(Authentication::getName)
//	                                .map((username) -> username.equals(context.getVariables().get("username")))
//	                                .map(AuthorizationDecision::new)
//	                        )
						.pathMatchers(HttpMethod.POST, "/auth/signup", "/auth/signin").permitAll()
						.pathMatchers("/auth/**").authenticated()
						.pathMatchers("/fruits/**").permitAll()
						.pathMatchers("/users/**").permitAll()
						.pathMatchers("/hello").permitAll()
						//.pathMatchers("/auth/**").permitAll()
						);
		//By default, the csrf protection is not disabled
    	//Cross origin request will be rejected by returning a status code 403 forbidden
    	//https://docs.spring.io/spring-security/reference/reactive/exploits/csrf.html
    	//https://www.baeldung.com/java-spring-fix-403-error
    	//This configuration only disables the csrf protection on this filter chain, it will not affect other filter chains
		http.csrf(csrf -> csrf.disable());
		// any other request requires the user to be authenticated);
		return http.build();
	}

	/**
	 * All requests must come from a authenticated user
	 * @param http
	 * @return
	 */
//	@Bean
//	public SecurityWebFilterChain generalFilterChain(ServerHttpSecurity http) {
//		http.httpBasic(Customizer.withDefaults());
//		http.securityMatcher(new PathPatternParserServerWebExchangeMatcher("/**"))
//				.authorizeExchange((exchanges) -> exchanges
//	                		.anyExchange()
//	                        .authenticated());
//		return http.build();
//	}
	@Bean
	public MapReactiveUserDetailsService userDetailsService() {
		UserDetails user1 = User.withUsername("username").password(encoder().encode("password")).roles("USER").build();
		UserDetails user2 = User.withUsername("manolo").password(encoder().encode("1234")).roles("USER").build();
		return new MapReactiveUserDetailsService(user1, user2);
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
	 * 
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
		// corsConfig.setAllowedMethods(Arrays.asList("POST"));
		corsConfig.setAllowedMethods(Arrays.asList("GET", "UPDATE", "DELETE", "POST"));
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
	
	
	
//	@Bean
//    public SecurityWebFilterChain filterChain(ServerHttpSecurity http) {
//        final var permitAll = new String[]{"/user-svr/country/list"};
//        return http.csrf(c->c.disable())
//                .httpBasic(c->c.disable())
//                .formLogin(c->c.disable())
//                .authorizeExchange(
//                		exchanges->exchanges
//                		.pathMatchers(permitAll).permitAll()
//                        .pathMatchers("/**").hasAuthority("USER")
//                        .anyExchange().authenticated()
//                        )
//                .authorizeExchange(exchanges->exchanges.and().addFilterAt(jwsFilter, SecurityWebFiltersOrder.AUTHENTICATION))
//                
//                .build();
//    }
}
