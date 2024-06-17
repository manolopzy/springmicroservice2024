package com.worldexplorers.happylearning.gateway.authorization.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.server.SecurityWebFilterChain;

import com.worldexplorers.happylearning.gateway.authorization.user.User;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {
//	@Bean
//	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//		http
//			.authorizeHttpRequests((requests) -> requests
//					.requestMatchers("/auth/**").permitAll()
//					
//				.requestMatchers("/", "/home").permitAll()
//				.anyRequest().authenticated()
//			)
//			.formLogin((form) -> form
//				.loginPage("/login")
//				.permitAll()
//			)
//			.logout((logout) -> logout.permitAll());
//
//		return http.build();
//	}
	
	@Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
		http
        .authorizeExchange(exchanges -> exchanges
        	.pathMatchers("/games/**").hasRole("USER")
            .pathMatchers("/auth/**").permitAll()
            .anyExchange().permitAll()
        )
        .httpBasic(Customizer.withDefaults())
        .formLogin(formLogin -> formLogin
            .loginPage("/login")
        );
		return http.build();
//        http
//            .authorizeExchange()
//                .pathMatchers("/process_login", "/login", "/logout").permitAll()
//                .pathMatchers("/api/team").permitAll()
//                .anyExchange().authenticated()
//            .and()
//                .addFilterAt(webFilter(), SecurityWebFiltersOrder.AUTHORIZATION)
//                .addFilterAt(new AuthorizationModifierFilter(),SecurityWebFiltersOrder.AUTHENTICATION)
//            ;
//        http.httpBasic().disable()
//            .formLogin().disable()
//            .logout().disable();
//
//        return http.build();
    }
	
	@Bean
	public UserDetailsService userDetailsService(PasswordEncoder encoder) {
		User user = new User("username", encoder.encode("password"), null, null, null, null, null, null);
		return new InMemoryUserDetailsManager(user);
	}
//
////	@Autowired
////	private UserDetailsService userDetailsService;
//
//	/**
//	 * This bean is used for user authentication purpose by Spring Security
//	 * 
//	 * Return a lamdda implementation of the interface
//	 * 
//	 * 
//	 * @param userRepo
//	 * @return
//	 */
//	@Bean
//	UserDetailsService userDetailsService(UserRepository userRepo) {
//		return username -> userRepo.findByUsername(username);
//	}
//
	/**
	 * The user password stored in a database should always be encoded, for that, we
	 * can use different password encoder algorithms for user authentication at
	 * login as well as for new user creation. What the password encoders do is
	 * apply an algorithm to transform the real password provided by the user to a
	 * different string. Once the algorithm is chosen, we have to use the same one.
	 * 
	 * 
	 * {@link SCryptPasswordEncoder} : applies Scrypt hashing encryption algorithm
	 * 
	 * {@link BCryptPasswordEncoder} : applies bscrypt strong hashing encryption
	 * 
	 * @return
	 */
	@Bean
	PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

}
