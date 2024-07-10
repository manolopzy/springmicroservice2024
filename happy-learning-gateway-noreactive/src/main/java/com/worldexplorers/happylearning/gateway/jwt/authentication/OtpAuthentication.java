package com.worldexplorers.happylearning.gateway.jwt.authentication;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

/**
 * This class represents a one-time password authentication token
 */
public class OtpAuthentication extends UsernamePasswordAuthenticationToken {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8611867482768187523L;

	public OtpAuthentication(Object principal, Object credentials) {
		super(principal, credentials);
	}

	public OtpAuthentication(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
		super(principal, credentials, authorities);
	}

}
