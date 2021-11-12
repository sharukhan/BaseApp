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
			@GetMapping(value = "/listAll", produces = "application/json", consumes = "application/json")
			public ResponseEntity<List<Client>> getAllClientDetail(){
				try {
					List<Client> client = clientService.getAllClientDetail();				
					return new ResponseEntity<>(client, HttpStatus.OK);
				}
				catch (Exception e) {
				      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
				    }
			}
			
			//get client by id
			@GetMapping(value = "/{id}", produces = "application/json", consumes = "application/json")
			public ResponseEntity<Client> getClientById(@PathVariable(value = "id") Long clientId)
				throws ResourceNotFoundException {
				try {
					return new ResponseEntity<>(clientService.getClientById(clientId),HttpStatus.OK);
				}
				catch (Exception e) {
				      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
				    }
			}
			
			//save client
			@PostMapping(value = "/create", produces = "application/json", consumes = "application/json")
			public ResponseEntity<Client> createClient(@RequestBody Client client) {
				try {
					Client client1 = clientService.createClient(client);
					return new ResponseEntity<>(client1,HttpStatus.CREATED);
				}
				catch (Exception e) {
				      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
				    }
			}
			
			//update client
			@PutMapping(value = "/update/{id}", produces = "application/json", consumes = "application/json")
			public ResponseEntity<Client> updateClient(@PathVariable(value = "id") Long clientId, @Validated @RequestBody Client clientDetail) throws ResourceNotFoundException{				
				try {
					clientService.updateClient(clientId, clientDetail);
					return new ResponseEntity<>(HttpStatus.OK);
				}
				catch (Exception e) {
				      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
				    }
			}
			
			//delete client
			@DeleteMapping(value = "/delete/{id}", produces = "application/json", consumes = "application/json")
			public ResponseEntity<Client> deleteClient(@PathVariable(value = "id") Long clientId) throws ResourceNotFoundException {				
				try {
					clientService.deleteClient(clientId);
					return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				}
				catch (Exception e) {
				      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
				    }				
			}

}
