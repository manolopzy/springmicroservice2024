package com.worldexplorers.happylearning.gateway.jwt.authentication.filter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.worldexplorers.happylearning.gateway.jwt.authentication.OtpAuthentication;
import com.worldexplorers.happylearning.gateway.jwt.authentication.UsernamePasswordAuthentication;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class InitialAuthenticationFilter extends OncePerRequestFilter {

	@Value("${jwt.signing.key}")
	private String signingKey;
	
	@Autowired
	private AuthenticationManager manager;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String username = request.getHeader("username");
		String password = request.getHeader("password");
		String code = request.getHeader("code");
		//if the code header is null, then the request represents username and password authentication step
		if (code == null) {
			Authentication a = new UsernamePasswordAuthentication(username, password);
			//The authentication logic is provided by an authentication provider
			//{@link UsernamePasswordAuthenticationProvider}
			manager.authenticate(a);
		} else {
			Authentication a = new OtpAuthentication(username, code);
			//{@link OtpAuthenticationProvider}
			a = manager.authenticate(a);
			SecretKey key = Keys.hmacShaKeyFor(signingKey.getBytes(StandardCharsets.UTF_8));
			String jwt = Jwts.builder().claims(Map.of("username", username)).signWith(key).compact();
			response.setHeader("Authorization", jwt);
		}
	}

	/**
	 * This filter only acts on "/login" requests
	 */
	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) {
		return !request.getServletPath().equals("/login");
	}

}
