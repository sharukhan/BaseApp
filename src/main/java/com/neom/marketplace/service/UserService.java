package com.neom.marketplace.service;

import java.util.ArrayList;
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

import com.neom.marketplace.exception.ResourceNotFoundException;
import com.neom.marketplace.model.Bim;
import com.neom.marketplace.model.User;
import com.neom.marketplace.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	
		public List<User> getAllUser(){
			List<User> user = new ArrayList<>();
			userRepository.findAll().forEach(user::add);
			return user;
		}
		
		
		public User getUserById(Long userId)
			throws ResourceNotFoundException {
			User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found:" + userId));
			return user;
			
		}
		
		public User createUser(User user) {
			return this.userRepository.save(user);
		}
		
		public User updateUser(Long userId, User userDetail) throws ResourceNotFoundException{			
			User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found:" + userId));
			user.setRole_Id(userDetail.getRole_Id());
			user.setName(userDetail.getName());
			user.setTitle(userDetail.getTitle());
			user.setEmail(userDetail.getEmail());
			user.setSupplier_Id(userDetail.getSupplier_Id());
			user.setClient_Id(userDetail.getClient_Id());
			return this.userRepository.save(user);			
		}
		
		public void deleteUser(Long userId) throws ResourceNotFoundException {			
			User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found:" + userId));
			this.userRepository.delete(user);
		}

}
