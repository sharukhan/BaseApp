package com.example.demo.service;

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

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Bim;
import com.example.demo.model.Provider;
import com.example.demo.repository.ProviderRepository;

@Service
public class ProviderService {
	
	@Autowired
	ProviderRepository providerRepository;
	
	public List<Provider> getAllProviders(){
		List<Provider> provider = new ArrayList<>();
		providerRepository.findAll().forEach(provider::add);
		return provider;
	}
	
	public Provider getProviderById(Long providerId)
		throws ResourceNotFoundException {
		Provider provider = providerRepository.findById(providerId).orElseThrow(() -> new ResourceNotFoundException("Provider not found:" + providerId));
		return provider;
	}
	
	public Provider createProvider(Provider provider) {
		return this.providerRepository.save(provider);
	}
	
	public Provider updateProvider(Long providerId,Provider providerDetail) throws ResourceNotFoundException{		
		Provider provider = providerRepository.findById(providerId).orElseThrow(() -> new ResourceNotFoundException("Provider not found:" + providerId));
		provider.setName(providerDetail.getName());
		provider.setLogo_Url(providerDetail.getLogo_Url());
		return this.providerRepository.save(provider);		
	}	

	public void deleteProvider(Long providerId) throws ResourceNotFoundException {		
		Provider provider = providerRepository.findById(providerId).orElseThrow(() -> new ResourceNotFoundException("Provider not found:" + providerId));
		this.providerRepository.delete(provider);		
	}
}
