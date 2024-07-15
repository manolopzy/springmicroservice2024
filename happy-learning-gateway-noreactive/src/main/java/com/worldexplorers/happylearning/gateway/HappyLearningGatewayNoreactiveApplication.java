package com.worldexplorers.happylearning.gateway;

import java.util.List;

import javax.crypto.SecretKey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.client.RestTemplate;

import com.worldexplorers.happylearning.gateway.jwt.authentication.User;
import com.worldexplorers.happylearning.gateway.jwt.authentication.filter.JwtAuthenticationFilter;

import io.jsonwebtoken.Jwts;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.Filter;
@EnableDiscoveryClient
@SpringBootApplication
public class HappyLearningGatewayNoreactiveApplication {
	private Logger logger = LoggerFactory.getLogger(HappyLearningGatewayNoreactiveApplication.class);
	@Autowired
	@Qualifier("springSecurityFilterChain")
	private Filter springSecurityFilterChain;
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	public static void main(String[] args) {
		SecretKey secret = Jwts.SIG.HS256.key().build();
		System.out.println("secret = " + secret.getAlgorithm().toString());
		SpringApplication.run(HappyLearningGatewayNoreactiveApplication.class, args);
	}
	public void getFilters() {
	    FilterChainProxy filterChainProxy = (FilterChainProxy) springSecurityFilterChain;
	    List<SecurityFilterChain> list = filterChainProxy.getFilterChains();
	    list.stream()
	      .flatMap(chain -> chain.getFilters().stream()) 
	      .forEach(filter -> logger.info(filter.getClass().getName()));
	}
	@PostConstruct
	public void init() {
		addUser("manolo", "1234");
		addUser("manuel", "1234");
		addUser("jose", "1234");
		
		getFilters();
	}
	@Value("${auth.server.base.url}")
	private String baseUrl;
	@Value("${auth.server.user.add}")
	private String addUser;
	org.springframework.security.web.access.intercept.AuthorizationFilter authorizationFilter;
	public void addUser(String username, String password) {
		String url = baseUrl + addUser;
		User body = new User();
		body.setUsername(username);
		body.setPassword(password);

		var request = new HttpEntity<>(body);
		restTemplate.postForEntity(url, request, Void.class);
	}
}
