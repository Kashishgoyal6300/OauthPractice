package com.auth.auth_app.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth.auth_app.dto.UserDto;
import com.auth.auth_app.service.UserServices;
import com.auth.auth_app.service.impl.UserServiceImpl;


@RestController
@RequestMapping("/api/v1/users")
public class UserController {
	
	
	public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }
	private  UserServiceImpl userService;
	 @PostMapping
	 public ResponseEntity<UserDto>  createUser(@RequestBody UserDto userDto) {
		return  ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userDto));
	 }
	 
	 @GetMapping
	 public ResponseEntity<Iterable<UserDto>>getAllUsers(){
		 return ResponseEntity.ok(userService.getAllUsers());
	 }
	 
	 @GetMapping("/email/{emailId}")
	 public ResponseEntity<UserDto> getUserByEmail(@PathVariable("email") String email){
		 return ResponseEntity.ok(userService.getUserByEmail(email));
	 }
	 
	 @DeleteMapping("/{userId}")
	 public void deleteUser(@PathVariable("userId") String userId ) {
		 userService.deleteUser(userId);
	 }

	 @PutMapping("/{userId}")
	 public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto,@PathVariable("userId") String userId){
		 return ResponseEntity.ok(userService.updateUser(userDto, userId));
	 }
}
