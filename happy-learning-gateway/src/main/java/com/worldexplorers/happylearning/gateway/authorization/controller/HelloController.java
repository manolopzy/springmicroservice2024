package com.worldexplorers.happylearning.gateway.authorization.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.worldexplorers.happylearning.gateway.authorization.user.User;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/hello", produces = MediaType.APPLICATION_JSON_VALUE)
public class HelloController {
	@GetMapping
	public ResponseEntity<Mono<String>> helloWorld() {
		return new ResponseEntity<Mono<String>>(Mono.just("hello world"), HttpStatus.OK);
	}
	@PostMapping
	public ResponseEntity<Mono<String>> signup(@RequestBody User user) {
		System.out.println("signup = " + user);
		return new ResponseEntity<Mono<String>>(Mono.just("hello " + user.getUsername()), HttpStatus.OK);
	}
}
