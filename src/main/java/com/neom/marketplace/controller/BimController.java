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
import com.neom.marketplace.repository.BimRepository;
import com.neom.marketplace.service.BimService;
import com.neom.marketplace.service.SupplierService;

@RestController
@RequestMapping("/api/v1/bim")
@Validated
public class BimController {
	
	@Autowired
	private  BimRepository bimRepository;
	
	@Autowired
	private BimService bimService;
	
	@Autowired
	SupplierService supplierService;
	
	public BimController(BimService bimService, SupplierService supplierService) {
		this.bimService = bimService;
	}
	
	//get bim
		@GetMapping(value = {"", "/"}, produces = "application/json", consumes = "application/json")
		public ResponseEntity<List<Bim>> getAllBimDetail(){
			try {
				List<Bim> bim = bimService.getAllBimDetail();
				for(Bim i : bim) {
					i.setSupplier_id(i.getSupplier().getId());
				}
				return new ResponseEntity<>(bim, HttpStatus.OK);
			}
			catch (Exception e) {
			      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			    }
		}
		
		//get bim by id
//		@GetMapping(value = "/{id}", produces = "application/json", consumes = "application/json")
//		public ResponseEntity<Bim> getBimById(@PathVariable(value = "id") Long bimId)
//			throws ResourceNotFoundException {
//			try {
//				return new ResponseEntity<>(bimService.getBimById(bimId),HttpStatus.OK);
//			}
//			catch (Exception e) {
//			      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//			    }
//		}
		
		//save bim
		@PostMapping(value = {"", "/"}, produces = "application/json", consumes = "application/json")
		public ResponseEntity<APIResponse> createBim(@Valid @RequestBody Bim bim, 
				BindingResult br) throws RequestValidationException, ResourceNotFoundException {
			
			try {
				try {
					Bim bimCheck = bimService.getBimById(bim.getId());
					if(bimCheck != null) throw new RequestValidationException(
							Constant.ON_CREATE_DUP_ERROR);
				} catch (ResourceNotFoundException rnfx) {
					bim.setId(0);
				}
				Supplier supplier = supplierService.getProviderById(bim.getSupplier_id());
				bim.setSupplier(supplier);
				
				Bim bim1 = bimService.createBim(bim);
				
				APIResponse resp = new APIResponse();
				return new ResponseEntity<>(resp,HttpStatus.CREATED);
			}
			catch (ResourceNotFoundException rnfx) {
				throw rnfx;
			}
			catch (Exception e) {
			      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			    }
		}
		
		//update bim
		@PutMapping(value = "/{id}", produces = "application/json", consumes = "application/json")
		public ResponseEntity<APIResponse> updateBim(@PathVariable(value = "id") String id, 
				@Valid @RequestBody Bim bimDetail,
				BindingResult br) throws ResourceNotFoundException, RequestValidationException {			
			
			try {
				Long bimId = Long.parseLong(id);
				Bim bim = bimService.getBimById(bimId);
				Supplier supplier = supplierService.getProviderById(bimDetail.getSupplier_id());
				bimDetail.setSupplier(supplier);
				bimService.updateBim(bimId, bimDetail);
				
				APIResponse resp = new APIResponse(Constant.Entities.Bim);
				return new ResponseEntity<>(resp, HttpStatus.OK);
			}
			catch (NumberFormatException nfe) {
				throw new RequestValidationException(
						String.format(Constant.ON_PUT_INPUT_ERROR, Constant.Entities.Bim));
			}
			catch (ResourceNotFoundException rnf) {
				throw rnf;
			}
			catch (Exception e) {
			      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			    }			
		}
		
		//delete bim
		@DeleteMapping(value = "/{id}", produces = "application/json")
		public ResponseEntity<Bim> deleteBim(@PathVariable(value = "id") String id
				) throws ResourceNotFoundException, RequestValidationException {
			try {
				Long bimId = Long.parseLong(id);
				bimService.getBimById(bimId);
				bimService.deleteBim(bimId);
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			catch (NumberFormatException nfe) {
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
