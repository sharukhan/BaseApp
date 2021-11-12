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
import com.example.demo.model.Provider;
import com.example.demo.repository.ProviderRepository;
import com.example.demo.service.ProviderService;

@RestController
@RequestMapping("/api/v1/provider")
public class ProviderController {
	
	@Autowired
	private ProviderRepository providerRepository;
	
	@Autowired
	private ProviderService providerService;
	
	//get provider 
			@GetMapping(value = "/listAll", produces = "application/json", consumes = "application/json")
			public ResponseEntity<List<Provider>> getAllProviders(){
				try {
					List<Provider> provider = providerService.getAllProviders();				
					return new ResponseEntity<>(provider, HttpStatus.OK);
				}
				catch (Exception e) {
				      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
				    }
			}
			
			//get provider by id
			@GetMapping(value = "/{id}", produces = "application/json", consumes = "application/json")
			public ResponseEntity<Provider> getProviderById(@PathVariable(value = "id") Long providerId)
				throws ResourceNotFoundException {
				try {
					return new ResponseEntity<>(providerService.getProviderById(providerId),HttpStatus.OK);
				}
				catch (Exception e) {
				      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
				    }
			}
			
			//save provider
			@PostMapping(value = "/create", produces = "application/json", consumes = "application/json")
			public ResponseEntity<Provider> createProvider(@RequestBody Provider provider) {
				try {
					Provider provider1 = providerService.createProvider(provider);
					return new ResponseEntity<>(provider1,HttpStatus.CREATED);
				}
				catch (Exception e) {
				      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
				    }
				}
			
			//update provider
			@PutMapping(value = "/update/{id}", produces = "application/json", consumes = "application/json")
			public ResponseEntity<Provider> updateProvider(@PathVariable(value = "id") Long providerId, @Validated @RequestBody Provider providerDetail) throws ResourceNotFoundException{				
				try {
					providerService.updateProvider(providerId, providerDetail);
					return new ResponseEntity<>(HttpStatus.OK);
				}
				catch (Exception e) {
				      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
				    }			
			}
			
			//delete Provider
			@DeleteMapping(value = "/delete/{id}", produces = "application/json", consumes = "application/json")
			public ResponseEntity<Provider> deleteProvider(@PathVariable(value = "id") Long providerId) throws ResourceNotFoundException {				
				try {
					providerService.deleteProvider(providerId);
					return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				}
				catch (Exception e) {
				      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
				    }				
			}

}
