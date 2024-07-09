package com.worldexplorers.happylearning.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Otp {
	@Id
	private String username;
	private String code;
}
