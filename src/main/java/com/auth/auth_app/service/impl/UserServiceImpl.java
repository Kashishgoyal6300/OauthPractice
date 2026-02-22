package com.auth.auth_app.service.impl;

import java.time.Instant;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.auth.auth_app.dto.UserDto;
import com.auth.auth_app.entity.Provider;
import com.auth.auth_app.entity.User;
import com.auth.auth_app.exceptions.ResourceNotFoundException;
import com.auth.auth_app.helpers.userHelper;
import com.auth.auth_app.repository.UserRepsository;
import com.auth.auth_app.service.UserServices;

@Service

public class UserServiceImpl implements  UserServices{
	
	public UserServiceImpl(UserRepsository userRepsository,ModelMapper modelMapper) {
		this.userRepsository=userRepsository;
		this.modelMapper=modelMapper;
	}
	private ModelMapper modelMapper;
	private UserRepsository userRepsository;
	
	@Override
	public UserDto createUser(UserDto userDto) {
		if(userDto.getEmail()==null || userDto.getEmail().isBlank()) {
			throw new IllegalArgumentException("Email is required");
		}
		if(userRepsository.existsByEmail(userDto.getEmail())) {
			throw new IllegalArgumentException("email is already exist");
		}
		
		User user=modelMapper.map(userDto,User.class);
		user.setProvider(userDto.getProvider()!=null ? userDto.getProvider():Provider.LOCAL);
		//userDto.setRoles();
		User savedUser=userRepsository.save(user);
		return modelMapper.map(savedUser, UserDto.class);
	}
	

	@Override
	public UserDto getUserByEmail(String email) {
	User user=	userRepsository.findByEmail(email)
		.orElseThrow(()->new ResourceNotFoundException("user not found"));
		return modelMapper.map(user, UserDto.class);
	}

	@Override
	public UserDto updateUser(UserDto userDto, String userId) {
		UUID uid=userHelper.parseUUID(userId);
		User existingUser=userRepsository.findById(uid)
				.orElseThrow(()-> new ResourceNotFoundException("User not found with given id"));
		
		if(userDto.getName()!=null) existingUser.setName(userDto.getName());
		if(userDto.getImage()!=null) existingUser.setImage(userDto.getImage());
		if(userDto.getProvider()!=null) existingUser.setProvider(userDto.getProvider());
		existingUser.setEnable(userDto.isEnable());
		if(userDto.getPassword()!=null) existingUser.setPassword(userDto.getPassword());
		existingUser.setUpdatedAt(Instant.now());
		User updatedUser=userRepsository.save(existingUser);
		return modelMapper.map(updatedUser, UserDto.class);
	}

	@Override
	public void deleteUser(String userId) {
		UUID uId= userHelper.parseUUID(userId);
		User user=userRepsository.findById(uId)
				.orElseThrow(()->new ResourceNotFoundException("user not found"));
		userRepsository.delete(user);
	}
	@Override
	public UserDto getUserById(String userId) {
	    User user = userRepsository.findById(userHelper.parseUUID(userId))
	        .orElseThrow(() -> new ResourceNotFoundException("User not exist for this id"));
	    return modelMapper.map(user, UserDto.class);
	}

	@Override
	public Iterable<UserDto> getAllUsers() {
	    return userRepsository.findAll()
	            .stream()
	            .map(user -> modelMapper.map(user, UserDto.class))
	            .toList();
	}
	
	
	
	

}
