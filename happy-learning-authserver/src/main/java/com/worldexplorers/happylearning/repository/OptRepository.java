package com.worldexplorers.happylearning.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.worldexplorers.happylearning.entity.Otp;

public interface OptRepository extends JpaRepository<Otp, String>{
	Optional<Otp> findOtpByUsername(String username);
}
