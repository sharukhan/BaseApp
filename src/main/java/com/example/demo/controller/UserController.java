package com.example.demo.controller;

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
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@RestController
@RequestMapping("/api/v1/")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	//get user 
	@GetMapping("users")
	public List<User> getAllUser(){
		return this.userRepository.findAll();
	}
	
	
	//get user by id
	@GetMapping("user/{id}")
	public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long userId)
		throws ResourceNotFoundException {
		User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found:" + userId));
		return ResponseEntity.ok().body(user);
	}
	
	//save user
	@PostMapping("createUser")
	public User createUser(@RequestBody User user) {
		return this.userRepository.save(user);
	}
	
	//update user
	@PutMapping("update/{id}")
	public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long userId, @Validated @RequestBody User userDetail) throws ResourceNotFoundException{
		
		User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found:" + userId));
		user.setfName(userDetail.getfName());
		user.setlName(userDetail.getlName());
		user.setEmail(userDetail.getEmail());
		return ResponseEntity.ok(this.userRepository.save(user));
		
	}
	//delete user
	@DeleteMapping("deleteUser/{id}")
	public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long userId) throws ResourceNotFoundException {
		
		User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found:" + userId));
		this.userRepository.delete(user);
		
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		
		return response;
		
	}

}
