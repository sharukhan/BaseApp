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
		@GetMapping(value = "/listAll", produces = "application/json", consumes = "application/json")
		public ResponseEntity<List<UserRole>> getAllUserRoleDetail(){
			try {
				List<UserRole> userRole = userRoleService.getAllUserRole();				
				return new ResponseEntity<>(userRole, HttpStatus.OK);
			}
			catch (Exception e) {
			      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			    }
		}
		
		//get BimInstance by id
		@GetMapping(value = "/{id}", produces = "application/json", consumes = "application/json")
		public ResponseEntity<UserRole> getUserRoleById(@PathVariable(value = "id") Long userRoleId)
			throws ResourceNotFoundException {
			try {
				return new ResponseEntity<>(userRoleService.getUserRoleById(userRoleId),HttpStatus.OK);
			}
			catch (Exception e) {
			      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			    }
		}
		
		//save BimInstance
		@PostMapping(value = "/create", produces = "application/json", consumes = "application/json")
		public ResponseEntity<UserRole> createUserRole(@RequestBody UserRole userRole) {
			try {
				UserRole userRole1 = userRoleService.createUserRole(userRole);
				return new ResponseEntity<>(userRole1,HttpStatus.CREATED);
			}
			catch (Exception e) {
			      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			    }
		}
		
		//update BimInstance
		@PutMapping(value = "/update/{id}", produces = "application/json", consumes = "application/json")
		public ResponseEntity<UserRole> updateUserRole(@PathVariable(value = "id") Long userRoleId, @Validated @RequestBody UserRole userRoleDetail) throws ResourceNotFoundException{
			
			try {
				userRoleService.updateUserRole(userRoleId, userRoleDetail);
				return new ResponseEntity<>(HttpStatus.OK);
			}
			catch (Exception e) {
			      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			    }
			
		}
		
		//delete BimInstance
		@DeleteMapping(value = "/delete/{id}", produces = "application/json", consumes = "application/json")
		public ResponseEntity<UserRole> deleteUserRole(@PathVariable(value = "id") Long userRoleId) throws ResourceNotFoundException {			
			try {
				userRoleService.deleteUserRole(userRoleId);
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			catch (Exception e) {
			      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			    }			
		}
}
