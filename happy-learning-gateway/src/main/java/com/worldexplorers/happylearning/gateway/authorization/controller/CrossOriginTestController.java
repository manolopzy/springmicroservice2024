package com.worldexplorers.happylearning.gateway.authorization.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

/**
 * The @CrossOrigin annotation has the following default configuration:
 * Allows all origins (that explains the ‘*’ value in the response header)
 * Allows all headers
 * All HTTP methods mapped by the handler method are allowed
 * Credentials are not enabled
 * The ‘max-age’ value is of 1800 seconds (30 minutes)
 */
//@CrossOrigin
@RestController
@RequestMapping
public class CrossOriginTestController {
	@CrossOrigin
	@PutMapping("/cors-enabled-endpoint")
	public Mono<String> corsEnabledEndpoint() {
		System.out.println("Test cors");
	   return Mono.just("Hello");
	}
	
}
