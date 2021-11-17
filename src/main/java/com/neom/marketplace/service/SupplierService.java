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
import com.neom.marketplace.model.Supplier;
import com.neom.marketplace.repository.SupplierRepository;

@Service
public class SupplierService {
	
	@Autowired
	SupplierRepository providerRepository;
	
	public List<Supplier> getAllProviders(){
		List<Supplier> provider = new ArrayList<>();
		providerRepository.findAll().forEach(provider::add);
		return provider;
	}
	
	public Supplier getProviderById(Long providerId)
		throws ResourceNotFoundException {
		Supplier provider = providerRepository.findById(providerId).orElseThrow(() -> new ResourceNotFoundException("Provider not found:" + providerId));
		return provider;
	}
	
	public Supplier createProvider(Supplier provider) {
		return this.providerRepository.save(provider);
	}
	
	public Supplier updateProvider(Long providerId,Supplier providerDetail) throws ResourceNotFoundException{		
		Supplier provider = providerRepository.findById(providerId).orElseThrow(() -> new ResourceNotFoundException("Provider not found:" + providerId));
		provider.setName(providerDetail.getName());
		provider.setLogoUrl(providerDetail.getLogoUrl());
		return this.providerRepository.save(provider);		
	}	

	public void deleteProvider(Long providerId) throws ResourceNotFoundException {		
		Supplier provider = providerRepository.findById(providerId).orElseThrow(() -> new ResourceNotFoundException("Provider not found:" + providerId));
		this.providerRepository.delete(provider);		
	}
}
