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
import com.example.demo.model.UserRole;
import com.example.demo.repository.UserRoleRepository;

@Service
public class UserRoleService {
	
	@Autowired
	private UserRoleRepository userRoleRepository;
	
	
			public List<UserRole> getAllUserRole(){
				return this.userRoleRepository.findAll();
			}
			
			public UserRole getUserRoleById(Long userRoleId)
				throws ResourceNotFoundException {
				UserRole userRole = userRoleRepository.findById(userRoleId).orElseThrow(() -> new ResourceNotFoundException("User Role not found:" + userRoleId));
				return userRole;
				
			}
			
			public UserRole createUserRole(UserRole userRole) {
				return this.userRoleRepository.save(userRole);
			}
			
			
			public UserRole updateUserRole(Long userRoleId, UserRole userRoleDetail) throws ResourceNotFoundException{
				
				UserRole userRole = userRoleRepository.findById(userRoleId).orElseThrow(() -> new ResourceNotFoundException("User Role not found:" + userRoleId));
				userRole.setName(userRoleDetail.getName());
				return this.userRoleRepository.save(userRole);
				
			}
			
			
			public Map<String, Boolean> deleteUserRole(Long userRoleId) throws ResourceNotFoundException {
				
				UserRole userRole = userRoleRepository.findById(userRoleId).orElseThrow(() -> new ResourceNotFoundException("User Role not found:" + userRoleId));
				this.userRoleRepository.delete(userRole);
				
				Map<String, Boolean> response = new HashMap<>();
				response.put("deleted", Boolean.TRUE);
				
				return response;
				
			}

}
