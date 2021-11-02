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
import com.example.demo.model.Provider;
import com.example.demo.repository.ProviderRepository;

@RestController
@RequestMapping("/api/v1/provider")
public class ProviderController {
	
	@Autowired
	private ProviderRepository providerRepository;
	
	//get provider 
			@GetMapping("/providers")
			public List<Provider> getAllProviders(){
				return this.providerRepository.findAll();
			}
			
			//get provider by id
			@GetMapping("/provider/{id}")
			public ResponseEntity<Provider> getProviderById(@PathVariable(value = "id") Long providerId)
				throws ResourceNotFoundException {
				Provider provider = providerRepository.findById(providerId).orElseThrow(() -> new ResourceNotFoundException("Provider not found:" + providerId));
				return ResponseEntity.ok().body(provider);
			}
			
			//save provider
			@PostMapping("/createProvider")
			public Provider createProvider(@RequestBody Provider provider) {
				return this.providerRepository.save(provider);
			}
			
			//update provider
			@PutMapping("/update/{id}")
			public ResponseEntity<Provider> updateProvider(@PathVariable(value = "id") Long providerId, @Validated @RequestBody Provider providerDetail) throws ResourceNotFoundException{
				
				Provider provider = providerRepository.findById(providerId).orElseThrow(() -> new ResourceNotFoundException("Provider not found:" + providerId));
				provider.setProviderLogoUrl(providerDetail.getProviderLogoUrl());
				return ResponseEntity.ok().body(provider);
				
			}
			
			//delete Provider
			@DeleteMapping("/deleteProvider/{id}")
			public Map<String, Boolean> deleteProvider(@PathVariable(value = "id") Long providerId) throws ResourceNotFoundException {
				
				Provider provider = providerRepository.findById(providerId).orElseThrow(() -> new ResourceNotFoundException("Provider not found:" + providerId));
				this.providerRepository.delete(provider);
				
				Map<String, Boolean> response = new HashMap<>();
				response.put("deleted", Boolean.TRUE);
				
				return response;
				
			}

}
