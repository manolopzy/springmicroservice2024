package com.worldexplorers.happylearning.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf(c->c.disable());
//		http.httpBasic(c->c.disable());
//		http.formLogin(c->c.disable());
		http.authorizeHttpRequests(requests->requests.anyRequest().permitAll());
		return http.build();
//        return http.authorizeHttpRequests(request -> request.anyRequest()
//                .authenticated())
//            .httpBasic(Customizer.withDefaults())
//            .build();
    }
	

}
