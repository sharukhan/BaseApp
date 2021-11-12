package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.example.demo.model.Bim;
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
	@GetMapping(value = "/list", produces = "application/json", consumes = "application/json")
	public ResponseEntity<List<User>> getAllUser(){
		try {
			List<User> user = userService.getAllUser();				
			return new ResponseEntity<>(user, HttpStatus.OK);
		}
		catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	}
	
	
	//get user by id
	@GetMapping(value = "/{id}" ,produces = "application/json", consumes = "application/json")
	public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long userId)
		throws ResourceNotFoundException {
		try {
			return new ResponseEntity<>(userService.getUserById(userId),HttpStatus.OK);
		}
		catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	}
	
	//save user
	@PostMapping(value = "/create", produces = "application/json", consumes = "application/json")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		try {
			User user1 = userService.createUser(user);
			return new ResponseEntity<>(user1,HttpStatus.CREATED);
		}
		catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	}
	
	//update user
	@PutMapping(value = "/update/{id}",produces = "application/json", consumes = "application/json")
	public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long userId, @Validated @RequestBody User userDetail) throws ResourceNotFoundException{
		
		try {
			userService.updateUser(userId, userDetail);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
		
	}
	//delete user
	@DeleteMapping(value = "/deleteUser/{id}", produces = "application/json", consumes = "application/json")
	public ResponseEntity<User> deleteUser(@PathVariable(value = "id") Long userId) throws ResourceNotFoundException {
		
		try {
			userService.deleteUser(userId);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
		
	}

}
