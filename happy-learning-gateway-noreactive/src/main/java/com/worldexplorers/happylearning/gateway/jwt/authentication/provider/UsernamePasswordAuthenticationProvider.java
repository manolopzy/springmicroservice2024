package com.worldexplorers.happylearning.gateway.jwt.authentication.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.worldexplorers.happylearning.gateway.jwt.authentication.UsernamePasswordAuthentication;
import com.worldexplorers.happylearning.gateway.jwt.authentication.proxy.AuthenticationServerProxy;

/**
 * This authentication provider is the implementation of the first step of the 
 * whole authentication process which consists of multiple steps in order to get 
 * a valid token to access the server resource
 * 
 * 
 */
@Component
public class UsernamePasswordAuthenticationProvider implements AuthenticationProvider{

	@Autowired
	private AuthenticationServerProxy authenticationServerProxy;
	
	/**
	 * The authentication manager call this authentication method
	 */
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
		String password = String.valueOf(authentication.getCredentials());
		authenticationServerProxy.sendAuth(username, password);
		//Return an token instance that has not yet authenticated 
		return new UsernamePasswordAuthenticationToken(username, password);
		
	}

	/**
	 * We have got multiple authentication steps, each step is represented by a 
	 * corresponding authentication class, in this authentication logic provider, 
	 * it uses {@link UsernamePasswordAuthentication}
	 * 
	 * The authentication manager calls this method 
	 */
	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthentication.class.isAssignableFrom(authentication);
	}

}
