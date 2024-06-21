package com.worldexplorers.happylearning;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.reactive.server.WebTestClient.ResponseSpec;

import com.worldexplorers.happylearning.gateway.authorization.controller.CrossOriginTestController;

public class CrossOriginTests {

	
	@Test
	public void allowedOriginsTest() {
		WebTestClient webTestClient = WebTestClient.bindToController(new CrossOriginTestController()).build();
		ResponseSpec response = webTestClient.put()
				  .uri("/cors-enabled-endpoint")
				  .header("Origin", "http://any-origin.com")
				  .header("Access-Control-Request-Method", "PUT")
				  .exchange();
		response.expectHeader()
		  .valueEquals("Access-Control-Allow-Origin", "*");
		
		response.expectHeader()
		  .valueEquals("Access-Control-Allow-Methods", "PUT");
		
		response.expectHeader()
		  .exists("Access-Control-Max-Age");
	}
	
}
