package com.worldexplorers.happylearning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.worldexplorers.happylearning.entity.AuthUser;
import com.worldexplorers.happylearning.entity.Otp;
import com.worldexplorers.happylearning.service.UserService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class UserAuthController {

	@Autowired
	private UserService userService;

	@PostMapping("/user/add")
	public void addUser(@RequestBody AuthUser user) {
		userService.addUser(user);
	}

	@PostMapping("/user/auth")
	public void auth(@RequestBody AuthUser user) {
		userService.auth(user);
	}

	@PostMapping("/otp/check")
	public void check(@RequestBody Otp otp, HttpServletResponse response) {
		if (userService.check(otp.getUsername(), otp.getCode())) {
			response.setStatus(HttpServletResponse.SC_OK);
		} else {
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		}
	}
}
