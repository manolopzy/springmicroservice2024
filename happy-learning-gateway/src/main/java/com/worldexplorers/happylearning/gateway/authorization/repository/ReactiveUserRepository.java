package com.worldexplorers.happylearning.gateway.authorization.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.worldexplorers.happylearning.gateway.authorization.user.User;

public interface ReactiveUserRepository extends ReactiveCrudRepository<User, Long>{
	User findByUsername(String username);
}
