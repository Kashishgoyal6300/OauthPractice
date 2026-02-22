package com.auth.auth_app.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.auth.auth_app.entity.User;
import com.auth.auth_app.exceptions.ResourceNotFoundException;
import com.auth.auth_app.repository.UserRepsository;
@Service
public class CustomUserDetailsService  implements UserDetailsService{
	
	public CustomUserDetailsService(UserRepsository userRepsository) {
		this.userRepsository=userRepsository;
	}
	
	private final UserRepsository userRepsository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user=userRepsository.findByEmail(username).orElseThrow(()->new ResourceNotFoundException("Invalid Email or Password"));
		return user;
	}

}
