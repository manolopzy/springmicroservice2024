package com.worldexplorers.happylearning.gateway.jwt.authentication.filter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.crypto.SecretKey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.worldexplorers.happylearning.gateway.jwt.authentication.UsernamePasswordAuthentication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * This filter is used to protect all resources that require a token for its access.
 * It also marks the end of the authentication process by creating an {@link UsernamePasswordAuthentication} 
 * passing all three parameters including the authorities.
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{

	@Value("${jwt.signing.key}")
	private String signingKey;
	private Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String jwt = request.getHeader("Authorization");
		logger.info("jwt = " + jwt);
		logger.info("headers = " + request.getHeaderNames());
		System.out.println("jwt = " + jwt);
		SecretKey key = Keys.hmacShaKeyFor(signingKey.getBytes(StandardCharsets.UTF_8));
		//Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
		
		Claims claims = (Claims) Jwts.parser().verifyWith(key).build().parse(jwt).getPayload();
		
		String username = String.valueOf(claims.get("username"));
		logger.info("username = " + username);
		System.out.println("username = " + username);
		GrantedAuthority a = new SimpleGrantedAuthority("user");
		
		var auth = new UsernamePasswordAuthentication(username, null, List.of(a));
		
		SecurityContextHolder.getContext().setAuthentication(auth);
		
		filterChain.doFilter(request, response);
	}

	/**
	 * Filtering all requests except "/login" actions
	 */
	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) {
		return request.getServletPath().equals("/login");
	}
}
