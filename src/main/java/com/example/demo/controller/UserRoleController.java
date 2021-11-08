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
import com.example.demo.model.UserRole;
import com.example.demo.repository.UserRoleRepository;
import com.example.demo.service.UserRoleService;

@RestController
@RequestMapping("/api/v1/userRole")
public class UserRoleController {
	
	@Autowired
	private UserRoleRepository userRoleRepository;
	
	@Autowired
	private UserRoleService userRoleService;
	
	//get All BimInstance
		@GetMapping("/listAllInstance")
		public List<UserRole> getAllBimInstanceDetail(){
			List<UserRole> userRoleDetail = new ArrayList<>();
			try {
				userRoleDetail = userRoleService.getAllUserRole();
			}
			catch (Exception e) {
				System.out.println(e);
			}
			
			return userRoleDetail;
		}
		
		//get BimInstance by id
		@GetMapping("/Detail/{id}")
		public UserRole getBimInstanceById(@PathVariable(value = "id") Long userRoleId)
			throws ResourceNotFoundException {
			UserRole userRole = new UserRole();
			try {
				userRole = userRoleService.getUserRoleById(userRoleId);
			}
			catch (Exception e) {
				System.out.println(e);
			}
			
			return userRole;
		}
		
		//save BimInstance
		@PostMapping("/createInstance")
		public UserRole createBimInstance(@RequestBody UserRole userRole) {
			try {
				userRoleService.createUserRole(userRole);
			}
			catch (Exception e) {
				System.out.println(e);
			}
			return userRole;
		}
		
		//update BimInstance
		@PutMapping("/updateInstance/{id}")
		public ResponseEntity<UserRole> updateBimInstance(@PathVariable(value = "id") Long userRoleId, @Validated @RequestBody UserRole userRoleDetail) throws ResourceNotFoundException{
			
			try {
				userRoleService.updateUserRole(userRoleId, userRoleDetail);
			}
			catch (Exception e) {
				System.out.println(e);
			}
			return null;
			
		}
		
		//delete BimInstance
		@DeleteMapping("/deleteInstance/{id}")
		public void deleteBimInstance(@PathVariable(value = "id") Long userRoleId) throws ResourceNotFoundException {
			
			try {
				userRoleService.deleteUserRole(userRoleId);
			}
			catch (Exception e) {
				System.out.println(e);
			}
			
		}

}
