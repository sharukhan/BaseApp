package com.neom.marketplace.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neom.marketplace.common.APIResponse;
import com.neom.marketplace.common.Constant;
import com.neom.marketplace.exception.RequestValidationException;
import com.neom.marketplace.exception.ResourceNotFoundException;
import com.neom.marketplace.model.Client;
import com.neom.marketplace.repository.ClientRepository;
import com.neom.marketplace.service.ClientService;

@RestController
@RequestMapping("/api/v1/client")
@Validated
public class ClientController {
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private ClientService clientService;
	
	
	//get client
	@GetMapping(value = {"", "/"}, produces = "application/json", consumes = "application/json")
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
//	@GetMapping(value = "/{id}", produces = "application/json", consumes = "application/json")
//	public ResponseEntity<Client> getClientById(@PathVariable(value = "id") Long clientId)
//		throws ResourceNotFoundException {
//		try {
//			return new ResponseEntity<>(clientService.getClientById(clientId),HttpStatus.OK);
//		}
//		catch (Exception e) {
//		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//		    }
//	}
	
	//save client
	@PostMapping(value = {"", "/"}, produces = "application/json", consumes = "application/json")
	public ResponseEntity<Object> createClient(@Valid @RequestBody Client client, 
			BindingResult br) throws RequestValidationException {
		
		try {
			try {
				Client clientCheck = clientService.getClientById(client.getId());
				if(clientCheck != null) throw new RequestValidationException(
						Constant.ON_CREATE_DUP_ERROR);
			} catch (ResourceNotFoundException rnfx) {
				client.setId(0);
			}
			
			Client client1 = clientService.createClient(client);
			APIResponse resp = new APIResponse();
			return new ResponseEntity<>(resp,HttpStatus.CREATED);
		}
		catch(RequestValidationException rve) {
			throw rve;
		}
		catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
	}
	
	//update client
	@PutMapping(value = "/{id}", produces = "application/json", consumes = "application/json")
	public ResponseEntity<APIResponse> updateClient(@PathVariable(value = "id") String id, 
			@Valid @RequestBody Client clientDetail, 
			BindingResult br) throws ResourceNotFoundException, RequestValidationException {
		
		try {
			Long clientId = Long.parseLong(id);
			clientService.getClientById(clientId);
			clientService.updateClient(clientId, clientDetail);
			
			APIResponse resp = new APIResponse(Constant.Entities.Client);
			return new ResponseEntity<>(resp, HttpStatus.OK);
		}
		catch (NumberFormatException nfe) {
			throw new RequestValidationException(
					String.format(Constant.ON_PUT_INPUT_ERROR, Constant.Entities.Client));
		}
		catch (ResourceNotFoundException rnf) {
			throw rnf;
		}
		catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	}
	
	//delete client
	@DeleteMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<Client> deleteClient(
			@PathVariable(value = "id") String id) throws ResourceNotFoundException, RequestValidationException {				
		
		try {
			Long clientId = Long.parseLong(id);
			clientService.getClientById(clientId);
			clientService.deleteClient(clientId);
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
		catch(NumberFormatException nfe) {
			throw new RequestValidationException(Constant.ON_DELETE_INPUT_ERROR);
		}
		catch (ResourceNotFoundException rnf) {
			throw rnf;
		}
		catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	}

}
