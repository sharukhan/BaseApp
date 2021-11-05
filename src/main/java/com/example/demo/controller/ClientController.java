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
import com.example.demo.model.Client;
import com.example.demo.repository.ClientRepository;

@RestController
@RequestMapping("/api/v1/client")
public class ClientController {
	
	@Autowired
	private ClientRepository clientRepository;
	
	
	//get client
			@GetMapping("/listAll")
			public List<Client> getAllClientDetail(){
				return this.clientRepository.findAll();
			}
			
			//get client by id
			@GetMapping("/Detail/{id}")
			public ResponseEntity<Client> getClientById(@PathVariable(value = "id") Long clientId)
				throws ResourceNotFoundException {
				Client client = clientRepository.findById(clientId).orElseThrow(() -> new ResourceNotFoundException("Client not found:" + clientId));
				return ResponseEntity.ok().body(client);
			}
			
			//save client
			@PostMapping("/createClient")
			public Client createClient(@RequestBody Client client) {
				return this.clientRepository.save(client);
			}
			
			//update client
			@PutMapping("/updateClient/{id}")
			public ResponseEntity<Client> updateClient(@PathVariable(value = "id") Long clientId, @Validated @RequestBody Client clientDetail) throws ResourceNotFoundException{
				
				Client client = clientRepository.findById(clientId).orElseThrow(() -> new ResourceNotFoundException("Client not found:" + clientId));
				client.setClientName(clientDetail.getClientName());
				client.setIndustryName(clientDetail.getIndustryName());
				client.setClientCountry(clientDetail.getClientCountry());
				return ResponseEntity.ok(this.clientRepository.save(client));
				
			}
			
			//delete client
			@DeleteMapping("/deleteClient/{id}")
			public Map<String, Boolean> deleteClient(@PathVariable(value = "id") Long clientId) throws ResourceNotFoundException {
				
				Client client = clientRepository.findById(clientId).orElseThrow(() -> new ResourceNotFoundException("Client not found:" + clientId));
				this.clientRepository.delete(client);
				
				Map<String, Boolean> response = new HashMap<>();
				response.put("deleted", Boolean.TRUE);
				
				return response;
				
			}

}
