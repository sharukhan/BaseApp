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
import com.example.demo.model.Client;
import com.example.demo.repository.ClientRepository;

@Service
public class ClientService {
	
	@Autowired
	ClientRepository clientRepository;
	
	public List<Client> getAllClientDetail(){
		List<Client> client = new ArrayList<>();
		clientRepository.findAll().forEach(client::add);
		return client;
	}
	
	public Client getClientById(Long clientId)
		throws ResourceNotFoundException {
		Client client = clientRepository.findById(clientId).orElseThrow(() -> new ResourceNotFoundException("Client not found:" + clientId));
		return client;
	}
	
	public Client createClient(Client client) {
		return this.clientRepository.save(client);
	}
	
	public Client updateClient(Long clientId, Client clientDetail) throws ResourceNotFoundException{		
		Client client = clientRepository.findById(clientId).orElseThrow(() -> new ResourceNotFoundException("Client not found:" + clientId));
		client.setName(clientDetail.getName());
		client.setIndustry(clientDetail.getIndustry());
		client.setCountry(clientDetail.getCountry());
		return this.clientRepository.save(client);		
	}
	

	public void deleteClient(Long clientId) throws ResourceNotFoundException {		
		Client client = clientRepository.findById(clientId).orElseThrow(() -> new ResourceNotFoundException("Client not found:" + clientId));
		this.clientRepository.delete(client);		
	}

}
