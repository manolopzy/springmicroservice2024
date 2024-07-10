package com.worldexplorers.happylearning.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

import com.worldexplorers.happylearning.gateway.jwt.authentication.User;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class HappyLearningGatewayNoreactiveApplication {

	@Autowired
	private RestTemplate restTemplate;
	
	
	public static void main(String[] args) {
		SpringApplication.run(HappyLearningGatewayNoreactiveApplication.class, args);
	}
	
	@PostConstruct
	public void init() {
		addUser("manolo", "1234");
	}
	@Value("${auth.server.base.url}")
	private String baseUrl;
	@Value("${auth.server.user.add}")
	private String addUser;
	
	public void addUser(String username, String password) {
		String url = baseUrl + addUser;
		User body = new User();
		body.setUsername(username);
		body.setPassword(password);

		var request = new HttpEntity<>(body);
		restTemplate.postForEntity(url, request, Void.class);
	}
}
