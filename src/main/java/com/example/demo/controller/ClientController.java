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
import com.example.demo.repository.ClientRepository;
import com.example.demo.service.ClientService;

@RestController
@RequestMapping("/api/v1/client")
public class ClientController {
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private ClientService clientService;
	
	
	//get client
			@GetMapping("/listAll")
			public List<Client> getAllClientDetail(){
				List<Client> clientDetail = new ArrayList<>();
				try {
					clientDetail = clientService.getAllClientDetail();
				}
				catch (Exception e) {
					System.out.println(e);
				}
				
				return clientDetail;
			}
			
			//get client by id
			@GetMapping("/Detail/{id}")
			public Client getClientById(@PathVariable(value = "id") Long clientId)
				throws ResourceNotFoundException {
				Client client = new Client();
				try {
					client = clientService.getClientById(clientId);
				}
				catch (Exception e) {
					System.out.println(e);
				}
				
				return client;
			}
			
			//save client
			@PostMapping("/createClient")
			public Client createClient(@RequestBody Client client) {
				try {
					clientService.createClient(client);
				}
				catch (Exception e) {
					System.out.println(e);
				}
				return client;
			}
			
			//update client
			@PutMapping("/updateClient/{id}")
			public ResponseEntity<Client> updateClient(@PathVariable(value = "id") Long clientId, @Validated @RequestBody Client clientDetail) throws ResourceNotFoundException{
				
				try {
					clientService.updateClient(clientId, clientDetail);
				}
				catch (Exception e) {
					System.out.println(e);
				}
				return null;
				
			}
			
			//delete client
			@DeleteMapping("/deleteClient/{id}")
			public void deleteClient(@PathVariable(value = "id") Long clientId) throws ResourceNotFoundException {
				
				try {
					clientService.deleteClient(clientId);
				}
				catch (Exception e) {
					System.out.println(e);
				}
				
			}

}
