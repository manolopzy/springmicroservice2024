package com.worldexplorers.happylearning.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.worldexplorers.happylearning.entity.AuthUser;

public interface UserRepository extends JpaRepository<AuthUser, String>{
	Optional<AuthUser> findUserByUsername(String username);
}
