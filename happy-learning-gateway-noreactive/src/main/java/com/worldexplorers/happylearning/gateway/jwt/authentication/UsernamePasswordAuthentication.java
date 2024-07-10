package com.worldexplorers.happylearning.gateway.jwt.authentication;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

/**
 * This is the class representing username and password authentication case
 */
public class UsernamePasswordAuthentication extends UsernamePasswordAuthenticationToken {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -1699871995433864810L;

	/**
	 * This constructor is used to create an authenticated instance which contains 
	 * all the details related to the authenticated user
	 * The state of isAuthenticated of the instance is true
	 * @param principal
	 * @param credentials
	 * @param authorities
	 */
	public UsernamePasswordAuthentication(Object principal, Object credentials,
			Collection<? extends GrantedAuthority> authorities) {
		super(principal, credentials, authorities);
	}

	/**
	 * This is used to create an unauthenticated authentication instance
	 * @param principal
	 * @param credentials
	 */
	public UsernamePasswordAuthentication(Object principal, Object credentials) {
		super(principal, credentials);
	}

}
