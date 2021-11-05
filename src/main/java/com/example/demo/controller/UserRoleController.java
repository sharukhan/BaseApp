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
import com.example.demo.model.UserRole;
import com.example.demo.repository.UserRoleRepository;

@RestController
@RequestMapping("/api/v1/userRole")
public class UserRoleController {
	
	@Autowired
	private UserRoleRepository userRoleRepository;
	
	//get All BimInstance
		@GetMapping("/listAllInstance")
		public List<UserRole> getAllBimInstanceDetail(){
			return this.userRoleRepository.findAll();
		}
		
		//get BimInstance by id
		@GetMapping("/Detail/{id}")
		public ResponseEntity<UserRole> getBimInstanceById(@PathVariable(value = "id") Long userRoleId)
			throws ResourceNotFoundException {
			UserRole userRole = userRoleRepository.findById(userRoleId).orElseThrow(() -> new ResourceNotFoundException("User Role not found:" + userRoleId));
			return ResponseEntity.ok().body(userRole);
		}
		
		//save BimInstance
		@PostMapping("/createInstance")
		public UserRole createBimInstance(@RequestBody UserRole userRole) {
			return this.userRoleRepository.save(userRole);
		}
		
		//update BimInstance
		@PutMapping("/updateInstance/{id}")
		public ResponseEntity<UserRole> updateBimInstance(@PathVariable(value = "id") Long userRoleId, @Validated @RequestBody UserRole userRoleDetail) throws ResourceNotFoundException{
			
			UserRole userRole = userRoleRepository.findById(userRoleId).orElseThrow(() -> new ResourceNotFoundException("User Role not found:" + userRoleId));
			userRole.setUserRole(userRoleDetail.getUserRole());
			return ResponseEntity.ok(this.userRoleRepository.save(userRole));
			
		}
		
		//delete BimInstance
		@DeleteMapping("/deleteInstance/{id}")
		public Map<String, Boolean> deleteBimInstance(@PathVariable(value = "id") Long userRoleId) throws ResourceNotFoundException {
			
			UserRole userRole = userRoleRepository.findById(userRoleId).orElseThrow(() -> new ResourceNotFoundException("User Role not found:" + userRoleId));
			this.userRoleRepository.delete(userRole);
			
			Map<String, Boolean> response = new HashMap<>();
			response.put("deleted", Boolean.TRUE);
			
			return response;
			
		}

}
