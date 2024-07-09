package com.worldexplorers.happylearning.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class AuthUser {
	@Id
	private String username;
	private String password;
}
