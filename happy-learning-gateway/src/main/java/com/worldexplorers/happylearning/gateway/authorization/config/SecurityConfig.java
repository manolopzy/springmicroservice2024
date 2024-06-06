package com.worldexplorers.happylearning.gateway.authorization.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

@Configuration
public class SecurityConfig {

//	@Bean
//	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//
//		httpSecurity.headers(headers -> headers.frameOptions(Customizer.withDefaults()).disable());
//
//		return httpSecurity.build();
//	}
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
