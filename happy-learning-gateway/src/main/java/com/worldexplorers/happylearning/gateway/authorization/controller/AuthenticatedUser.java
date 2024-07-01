package com.worldexplorers.happylearning.gateway.authorization.controller;

import com.worldexplorers.happylearning.gateway.authorization.user.RegistrationUser;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AuthenticatedUser {

	private final RegistrationUser registrationUser;
	private String jwt;
	
}
