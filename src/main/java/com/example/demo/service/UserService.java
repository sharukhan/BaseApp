package com.example.demo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	
		public List<User> getAllUser(){
			return this.userRepository.findAll();
		}
		
		
		public User getUserById(Long userId)
			throws ResourceNotFoundException {
			User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found:" + userId));
			return user;
			
		}
		
		public User createUser(@RequestBody User user) {
			return this.userRepository.save(user);
		}
		
		public User updateUser(Long userId, @Validated @RequestBody User userDetail) throws ResourceNotFoundException{
			
			User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found:" + userId));
			user.setRole_Id(userDetail.getRole_Id());
			user.setName(userDetail.getName());
			user.setTitle(userDetail.getTitle());
			user.setEmail(userDetail.getEmail());
			user.setSupplier_Id(userDetail.getSupplier_Id());
			user.setClient_Id(userDetail.getClient_Id());
			return this.userRepository.save(user);
			
		}
		
		public Map<String, Boolean> deleteUser(Long userId) throws ResourceNotFoundException {
			
			User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found:" + userId));
			this.userRepository.delete(user);
			
			Map<String, Boolean> response = new HashMap<>();
			response.put("deleted", Boolean.TRUE);
			
			return response;
			
		}

}
