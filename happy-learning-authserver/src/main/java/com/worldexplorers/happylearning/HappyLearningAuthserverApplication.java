package com.worldexplorers.happylearning;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.worldexplorers.happylearning.entity.Otp;
import com.worldexplorers.happylearning.repository.OptRepository;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class HappyLearningAuthserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(HappyLearningAuthserverApplication.class, args);
	}

	@Autowired
	private OptRepository optRepository;
	@PostConstruct
	public void init() {
		List<Otp> otps  = optRepository.findAll();
		for (Otp otp : otps) {
			System.out.println(otp.toString());
		}
		
	}
}
