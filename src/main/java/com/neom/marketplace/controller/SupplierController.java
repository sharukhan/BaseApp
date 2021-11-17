package com.neom.marketplace.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.neom.marketplace.model.Bim;
import com.neom.marketplace.model.Client;
import com.neom.marketplace.model.Supplier;
import com.neom.marketplace.repository.SupplierRepository;
import com.neom.marketplace.service.SupplierService;

@RestController
@RequestMapping("/api/v1/supplier")
@Validated
public class SupplierController {
	
	@Autowired
	private SupplierRepository supplierRepository;
	
	@Autowired
	private SupplierService supplierService;
	
	//get provider 
	@GetMapping(value = {"", "/"}, produces = "application/json", consumes = "application/json")
	public ResponseEntity<List<Supplier>> getAllProviders(){
		try {
			List<Supplier> provider = supplierService.getAllProviders();				
			return new ResponseEntity<>(provider, HttpStatus.OK);
		}
		catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	}
	
	//get provider by id
//	@GetMapping(value = "/{id}", produces = "application/json", consumes = "application/json")
//	public ResponseEntity<Supplier> getProviderById(@PathVariable(value = "id") Long providerId)
//		throws ResourceNotFoundException {
//		try {
//			return new ResponseEntity<>(supplierService.getProviderById(providerId),HttpStatus.OK);
//		}
//		catch (Exception e) {
//		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//		    }
//	}
	
	//save provider
	@PostMapping(value = {"", "/"}, produces = "application/json", consumes = "application/json")
	public ResponseEntity<APIResponse> createProvider(@Valid @RequestBody Supplier supplier, 
			BindingResult br) throws RequestValidationException {
		
		try {
			try {
				Supplier supplierCheck = supplierService.getProviderById(supplier.getId());
				if(supplierCheck != null) throw new RequestValidationException(
						Constant.ON_CREATE_DUP_ERROR);
			} catch (ResourceNotFoundException rnfx) {
				supplier.setId(0);
			}
			Supplier provider1 = supplierService.createProvider(supplier);
			APIResponse resp = new APIResponse();
			return new ResponseEntity<>(resp, HttpStatus.CREATED);
		}
		catch(RequestValidationException rve) {
			throw rve;
		}
		catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
		}
	
	//update provider
	@PutMapping(value = "/{id}", produces = "application/json", consumes = "application/json")
	public ResponseEntity<APIResponse> updateProvider(@PathVariable(value = "id") String id, 
			@Valid @RequestBody Supplier providerDetail, 
			BindingResult br) throws ResourceNotFoundException, RequestValidationException {
		
		try {
			Long supplierId = Long.parseLong(id);
			supplierService.getProviderById(supplierId);
			supplierService.updateProvider(supplierId, providerDetail);
			
			APIResponse resp = new APIResponse(Constant.Entities.Supplier);
			return new ResponseEntity<>(resp, HttpStatus.OK);
		}
		catch (NumberFormatException nfe) {
			throw new RequestValidationException(
					String.format(Constant.ON_PUT_INPUT_ERROR, Constant.Entities.Supplier));
		}
		catch (ResourceNotFoundException rnf) {
			throw rnf;
		}
		catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }			
	}
	
	//delete Provider
	@DeleteMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<Supplier> deleteProvider(@PathVariable(value = "id") String id
			) throws ResourceNotFoundException, RequestValidationException {				
		try {
			Long supplierId = Long.parseLong(id);
			supplierService.getProviderById(supplierId);
			supplierService.deleteProvider(supplierId);
			
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
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
