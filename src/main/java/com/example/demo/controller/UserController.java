package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Client;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;
	
	//get user 
	@GetMapping(value = "/users", produces = "application/json", consumes = "application/json")
	public List<User> getAllUser(){
		List<User> userDetail = new ArrayList<>();
		try {
			userDetail = userService.getAllUser();
		}
		catch (Exception e) {
			System.out.println(e);
		}
		
		return userDetail;
	}
	
	
	//get user by id
	@GetMapping("/user/{id}")
	public User getUserById(@PathVariable(value = "id") Long userId)
		throws ResourceNotFoundException {
		User user = new User();
		try {
			user = userService.getUserById(userId);
		}
		catch (Exception e) {
			System.out.println(e);
		}
		
		return user;
	}
	
	//save user
	@PostMapping("/createUser")
	public User createUser(@RequestBody User user) {
		try {
			userService.createUser(user);
		}
		catch (Exception e) {
			System.out.println(e);
		}
		return user;
	}
	
	//update user
	@PutMapping("/update/{id}")
	public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long userId, @Validated @RequestBody User userDetail) throws ResourceNotFoundException{
		
		try {
			userService.updateUser(userId, userDetail);
		}
		catch (Exception e) {
			System.out.println(e);
		}
		return null;
		
	}
	//delete user
	@DeleteMapping("/deleteUser/{id}")
	public void deleteUser(@PathVariable(value = "id") Long userId) throws ResourceNotFoundException {
		
		try {
			userService.deleteUser(userId);
		}
		catch (Exception e) {
			System.out.println(e);
		}
		
	}

}
