package com.worldexplorers.happylearning.gateway.authorization.repository;

import org.springframework.data.repository.CrudRepository;

import com.worldexplorers.happylearning.gateway.authorization.user.User;

public interface UserRepository extends CrudRepository<User, Long>{

	User findByUsername(String username);

}
