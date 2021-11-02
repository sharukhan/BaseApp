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
import com.example.demo.model.Bim;
import com.example.demo.repository.BimRepository;

@RestController
@RequestMapping("/api/v1/bim")
public class BimController {
	
	@Autowired
	private  BimRepository bimRepository;
	
	//get user 
		@GetMapping("users")
		public List<Bim> getAllBimUser(){
			return this.bimRepository.findAll();
		}
		
		//get user by id
		@GetMapping("user/{id}")
		public ResponseEntity<Bim> getBimUserById(@PathVariable(value = "id") Long bimId)
			throws ResourceNotFoundException {
			Bim bim = bimRepository.findById(bimId).orElseThrow(() -> new ResourceNotFoundException("Bim User not found:" + bimId));
			return ResponseEntity.ok().body(bim);
		}
		
		//save user
		@PostMapping("createUser")
		public Bim createBimUser(@RequestBody Bim bim) {
			return this.bimRepository.save(bim);
		}
		
		//update user
		@PutMapping("update/{id}")
		public ResponseEntity<Bim> updateBimUser(@PathVariable(value = "id") Long bimId, @Validated @RequestBody Bim bimDetail) throws ResourceNotFoundException{
			
			Bim bim = bimRepository.findById(bimId).orElseThrow(() -> new ResourceNotFoundException("Bim User not found:" + bimId));
			bim.setBimName(bimDetail.getBimName());
			return ResponseEntity.ok(this.bimRepository.save(bim));
			
		}
		
		//delete user
		@DeleteMapping("deleteUser/{id}")
		public Map<String, Boolean> deleteBimUser(@PathVariable(value = "id") Long bimId) throws ResourceNotFoundException {
			
			Bim bim = bimRepository.findById(bimId).orElseThrow(() -> new ResourceNotFoundException("Bim User not found:" + bimId));
			this.bimRepository.delete(bim);
			
			Map<String, Boolean> response = new HashMap<>();
			response.put("deleted", Boolean.TRUE);
			
			return response;
			
		}

}
