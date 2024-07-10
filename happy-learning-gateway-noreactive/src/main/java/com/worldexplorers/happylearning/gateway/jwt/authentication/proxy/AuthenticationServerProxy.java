package com.worldexplorers.happylearning.gateway.jwt.authentication.proxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.worldexplorers.happylearning.gateway.jwt.authentication.User;

/**
 * The {@link AuthenticationServerProxy} class is responsible for communicating 
 * with the authentication server
 */
@Component
public class AuthenticationServerProxy {

	@Autowired
	private RestTemplate restTemplate;

	@Value("${auth.server.base.url}")
	private String baseUrl;

	@Value("${auth.server.user.auth}")
	private String userAuth;
	
	@Value("${auth.server.otp.check}")
	private String otpCheck;
	
	public void sendAuth(String username, String password) {
		String url = baseUrl + userAuth;
		User body = new User();
		body.setUsername(username);
		body.setPassword(password);

		var request = new HttpEntity<>(body);
		restTemplate.postForEntity(url, request, Void.class);
	}

	public boolean sendOTP(String username, String code) {
		String url = baseUrl + otpCheck;
		User body = new User();
		body.setUsername(username);
		body.setCode(code);
		var request = new HttpEntity<>(body);
		var response = restTemplate.postForEntity(url, request, Void.class);

		return response.getStatusCode().equals(HttpStatus.OK);
	}
}
