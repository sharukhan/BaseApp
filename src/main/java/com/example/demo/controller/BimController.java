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
	
	//get bim user 
		@GetMapping("/users")
		public List<Bim> getAllBimUser(){
			return this.bimRepository.findAll();
		}
		
		//get bim user by id
		@GetMapping("/user/{id}")
		public ResponseEntity<Bim> getBimUserById(@PathVariable(value = "id") Long bimId)
			throws ResourceNotFoundException {
			Bim bim = bimRepository.findById(bimId).orElseThrow(() -> new ResourceNotFoundException("Bim User not found:" + bimId));
			return ResponseEntity.ok().body(bim);
		}
		
		//save bim user
		@PostMapping("/createUser")
		public Bim createBimUser(@RequestBody Bim bim) {
			return this.bimRepository.save(bim);
		}
		
		//update bim user
		@PutMapping("/update/{id}")
		public ResponseEntity<Bim> updateBimUser(@PathVariable(value = "id") Long bimId, @Validated @RequestBody Bim bimDetail) throws ResourceNotFoundException{
			
			Bim bim = bimRepository.findById(bimId).orElseThrow(() -> new ResourceNotFoundException("Bim User not found:" + bimId));
			bim.setBimSupplierId(bimDetail.getBimSupplierId());
			bim.setBimTitle(bimDetail.getBimTitle());
			bim.setBimInsightUrl(bimDetail.getBimInsightUrl());
			bim.setBimBenefitsUrl(bimDetail.getBimBenefitsUrl());
			bim.setBimOneLinerDescription(bimDetail.getBimOneLinerDescription());
			bim.setBimRating(bimDetail.getBimRating());
			bim.setBimLayoutType(bimDetail.getBimLayoutType());
			bim.setBimStatus(bimDetail.getBimStatus());
			bim.setBimContentId(bimDetail.getBimContentId());
			return ResponseEntity.ok(this.bimRepository.save(bim));
			
		}
		
		//delete bim user
		@DeleteMapping("/deleteUser/{id}")
		public Map<String, Boolean> deleteBimUser(@PathVariable(value = "id") Long bimId) throws ResourceNotFoundException {
			
			Bim bim = bimRepository.findById(bimId).orElseThrow(() -> new ResourceNotFoundException("Bim User not found:" + bimId));
			this.bimRepository.delete(bim);
			
			Map<String, Boolean> response = new HashMap<>();
			response.put("deleted", Boolean.TRUE);
			
			return response;
			
		}

}
