package com.worldexplorers.happylearning.gateway.authorization.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.worldexplorers.happylearning.gateway.authorization.repository.UserRepository;
import com.worldexplorers.happylearning.gateway.authorization.user.User;


@Controller
@RequestMapping("/register")
public class RegistrationController {
  
  private UserRepository userRepo;
  private PasswordEncoder passwordEncoder;

  public RegistrationController(
      UserRepository userRepo, PasswordEncoder passwordEncoder) {
    this.userRepo = userRepo;
    this.passwordEncoder = passwordEncoder;
  }
  /**
   * Ctrl + 1 to autogenerate a local variable to receive 
   * a return value
   * @param form
   * @return
   */
  @PostMapping
  public User processRegistration(RegistrationForm form) {
    User user = userRepo.save(form.toUser(passwordEncoder));
    
    return user;
  }

}

