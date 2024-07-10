package com.worldexplorers.happylearning.gateway.jwt.authentication;

import lombok.Data;

@Data
public class User {

	private String username;
	private String password;
	private String code;
	
}
