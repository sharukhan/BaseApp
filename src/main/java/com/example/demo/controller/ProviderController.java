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
			@GetMapping("/providers")
			public List<Provider> getAllProviders(){
				List<Provider> providerDetail = new ArrayList<>();
				try {
					providerDetail = providerService.getAllProviders();
				}
				catch (Exception e) {
					System.out.println(e);
				}
				
				return providerDetail;
			}
			
			//get provider by id
			@GetMapping("/provider/{id}")
			public Provider getProviderById(@PathVariable(value = "id") Long providerId)
				throws ResourceNotFoundException {
				Provider provider = new Provider();
				try {
					provider = providerService.getProviderById(providerId);
				}
				catch (Exception e) {
					System.out.println(e);
				}
				
				return provider;
			}
			
			//save provider
			@PostMapping("/createProvider")
			public Provider createProvider(@RequestBody Provider provider) {
				try {
					providerService.createProvider(provider);
				}
				catch (Exception e) {
					System.out.println(e);
				}
				return provider;
			}
			
			//update provider
			@PutMapping("/update/{id}")
			public ResponseEntity<Provider> updateProvider(@PathVariable(value = "id") Long providerId, @Validated @RequestBody Provider providerDetail) throws ResourceNotFoundException{
				
				try {
					providerService.updateProvider(providerId, providerDetail);
				}
				catch (Exception e) {
					System.out.println(e);
				}
				return null;
				
			}
			
			//delete Provider
			@DeleteMapping("/deleteProvider/{id}")
			public void deleteProvider(@PathVariable(value = "id") Long providerId) throws ResourceNotFoundException {
				
				try {
					providerService.deleteProvider(providerId);
				}
				catch (Exception e) {
					System.out.println(e);
				}
				
			}

}
