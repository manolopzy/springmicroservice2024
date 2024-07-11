package com.worldexplorers.happylearning.gateway;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

import com.worldexplorers.happylearning.gateway.jwt.authentication.User;

import io.jsonwebtoken.Jwts;
import jakarta.annotation.PostConstruct;
@EnableDiscoveryClient
@SpringBootApplication
public class HappyLearningGatewayNoreactiveApplication {

	
	@Autowired
	private RestTemplate restTemplate;
	
	
	public static void main(String[] args) {
		SecretKey secret = Jwts.SIG.HS256.key().build();
		System.out.println("secret = " + secret.getAlgorithm().toString());
		SpringApplication.run(HappyLearningGatewayNoreactiveApplication.class, args);
	}
	
	@PostConstruct
	public void init() {
		addUser("manolo", "1234");
		addUser("manuel", "1234");
		addUser("jose", "1234");
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
