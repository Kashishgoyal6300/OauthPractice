package com.auth.auth_app.service;



import com.auth.auth_app.dto.UserDto;


public interface UserServices {
	UserDto createUser(UserDto userDto);
	UserDto getUserByEmail(String email);
	UserDto updateUser(UserDto userDto,String userId);
	void deleteUser(String  userId);
	UserDto getUserById(String id);
	Iterable<UserDto>getAllUsers();
}
