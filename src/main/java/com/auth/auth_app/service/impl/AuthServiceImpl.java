package com.auth.auth_app.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth.auth_app.dto.UserDto;
import com.auth.auth_app.service.AuthService;
import com.auth.auth_app.service.UserServices;

@Service
public class AuthServiceImpl implements AuthService {
	private PasswordEncoder passwordEncoder;
	private final UserServices userServices;
	
	public AuthServiceImpl(UserServices userServices) {
		this.userServices=userServices;
	}
	
	
	@Override
	public UserDto registerUser(UserDto userDto) {
		userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
		UserDto userDto1=userServices.createUser(userDto);
		return userDto1;
	}

}
