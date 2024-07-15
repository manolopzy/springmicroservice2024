package com.worldexplorers.happylearning.gateway.jwt.authentication.filter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import javax.crypto.SecretKey;

import org.apache.hc.core5.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class InitialAuthenticationFilter extends OncePerRequestFilter {

	private Logger logger = LoggerFactory.getLogger(InitialAuthenticationFilter.class);
	@Value("${jwt.signing.key}")
	private String signingKey;
	
	@Autowired
	private AuthenticationManager authenticationManager;
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
			authenticationManager.authenticate(a);
			System.out.println("send response ok to client");
			logger.info("send response ok to client", username);
			response.setStatus(HttpStatus.SC_OK);
		} else {
			Authentication a = new OtpAuthentication(username, code);
			//{@link OtpAuthenticationProvider}
			a = authenticationManager.authenticate(a);
			SecretKey key = Keys.hmacShaKeyFor(signingKey.getBytes(StandardCharsets.UTF_8));
			String jwt = Jwts.builder().claims(Map.of("username", username)).signWith(key).compact();
			logger.info("set response header with code", code);
			System.out.println("send response ok to client" + code);
			//response.setHeader("Authorization", jwt);
			response.addHeader("Authorization", jwt);
			response.addCookie(new Cookie("Authorization", jwt));
			System.out.println("send response ok to client" + jwt);
			logger.info("set response header with jwt", jwt);
			
			//Location: $url-of-created-resource
			//Access-Control-Expose-Headers: Location
			response.addHeader("Location", "$url-of-created-resource");
			response.addHeader("Access-Control-Expose-Headers", "Location");
			/**
			 * there is a header limitation with cors requests which is different 
			 * from a request launched by cURL.
			 * Without using "Access-Control-Expose-Headers": "Authorization" to tell 
			 * the browser to expose a specific header or content returned as 
			 * header, it will automatically prevent newly added headers 
			 * from showing to a application developed by such as 
			 * Reactjs, Angular or other technologies from where the html .. 
			 * etc. static content is loaded.
			 */
			response.addHeader("Authorization", jwt);
			response.addHeader("Access-Control-Expose-Headers", "Authorization");
			
//			response.addHeader("Access-Control-Request-Headers", "Authorization");
//			response.addHeader("Authorization", jwt);
			
			
			//Access-Control-Request-Headers: SESSION_ID
//			SESSION_ID: $current_session_id
//			response.addHeader("Access-Control-Request-Headers", "Authorization");
			response.setStatus(HttpStatus.SC_CREATED);
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
