package com.worldexplorers.happylearning.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.worldexplorers.happylearning.entity.AuthUser;
import com.worldexplorers.happylearning.entity.Otp;
import com.worldexplorers.happylearning.repository.OptRepository;
import com.worldexplorers.happylearning.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private OptRepository otpRepository;

	public void addUser(AuthUser user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
	}

	/**
	 * Each time we authenticate a user's credentials, if they match 
	 * with the credential details stored in the database, we generate 
	 * a random code which is only valid for one time and sent to the 
	 * user through SMS or email
	 * @param user
	 */
	public void auth(AuthUser user) {
		Optional<AuthUser> o = userRepository.findUserByUsername(user.getUsername());
		if (o.isPresent()) {
			AuthUser u = o.get();
			if (passwordEncoder.matches(user.getPassword(), u.getPassword())) {
				String code = renewOtp(u);
				System.out.println("code = " + code);
				send(code, "", "");
			} else {
				throw new BadCredentialsException("Bad credentials.");
			}
		} else {
			throw new BadCredentialsException("Bad credentials.");
		}
	}

	private void send(String code, String phoenumber, String email) {
		// TODO Auto-generated method stub
		
	}

	private String renewOtp(AuthUser u) {
		String code = "5678";//GenerateCodeUtil.generateCode();
		Optional<Otp> userOtp = otpRepository.findOtpByUsername(u.getUsername());
		if (userOtp.isPresent()) {
			Otp otp = userOtp.get();
			otp.setCode(code);
		} else {
			Otp otp = new Otp();
			otp.setUsername(u.getUsername());
			otp.setCode(code);
			otpRepository.save(otp);
		}
		return code;
	}
	
	/**
	 * Check if the code sent by the user matches the code stored in the database
	 * @param username
	 * @param code
	 * @return
	 */
	public boolean check(String username, String code) {
		Optional<Otp> o = otpRepository.findOtpByUsername(username);
		if (o.isPresent()) {
			Otp otp = o.get();
			if (otp.getCode().equals(code)) {
				return true;
			}
		}
		return false;
	}
}
