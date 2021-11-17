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
import com.neom.marketplace.model.Role;
import com.neom.marketplace.repository.UserRoleRepository;

@Service
public class UserRoleService {
	
	@Autowired
	private UserRoleRepository userRoleRepository;
	
	
			public List<Role> getAllUserRole(){
				List<Role> userRole = new ArrayList<>();
				userRoleRepository.findAll().forEach(userRole::add);
				return userRole;
			}
			
			public Role getUserRoleById(Long userRoleId)
				throws ResourceNotFoundException {
				Role userRole = userRoleRepository.findById(userRoleId).orElseThrow(() -> new ResourceNotFoundException("User Role not found:" + userRoleId));
				return userRole;				
			}
			
			public Role createUserRole(Role userRole) {
				return this.userRoleRepository.save(userRole);
			}
			
			
			public Role updateUserRole(Long userRoleId, Role userRoleDetail) throws ResourceNotFoundException{				
				Role userRole = userRoleRepository.findById(userRoleId).orElseThrow(() -> new ResourceNotFoundException("User Role not found:" + userRoleId));
				userRole.setName(userRoleDetail.getName());
				return this.userRoleRepository.save(userRole);				
			}
			
			
			public void deleteUserRole(Long userRoleId) throws ResourceNotFoundException {				
				Role userRole = userRoleRepository.findById(userRoleId).orElseThrow(() -> new ResourceNotFoundException("User Role not found:" + userRoleId));
				this.userRoleRepository.delete(userRole);				
			}
}
