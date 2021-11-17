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
import com.neom.marketplace.model.Bim;
import com.neom.marketplace.model.BimInstance;
import com.neom.marketplace.model.Supplier;
import com.neom.marketplace.repository.BimInstanceRepository;
import com.neom.marketplace.service.BimInstanceService;
import com.neom.marketplace.service.BimService;

@RestController
@RequestMapping("/api/v1/bim_instance")
@Validated
public class BimInstanceController {
	
	@Autowired
	private BimInstanceRepository bimInstanceRepository;
	
	@Autowired
	private BimInstanceService bimInstanceService;
	
	@Autowired
	private BimService bimService;
	
	//get All BimInstance
	@GetMapping(value = {"", "/"}, produces = "application/json", consumes = "application/json")
	public ResponseEntity<List<BimInstance>> getAllBimInstanceDetail(){
		try {
			List<BimInstance> bimInstance = bimInstanceService.getAllBimInstanceDetail();
			for(BimInstance i : bimInstance) {
				i.setBim_id(i.getBim().getId());
			}
			return new ResponseEntity<>(bimInstance, HttpStatus.OK);
		}
		catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	}
	
	//get BimInstance by id
//	@GetMapping(value = "/{id}", produces = "application/json", consumes = "application/json")
//	public ResponseEntity<BimInstance> getBimInstanceById(@PathVariable(value = "id") Long bInsId)
//		throws ResourceNotFoundException {
//		try {
//			return new ResponseEntity<>(bimInstanceService.getBimInstanceById(bInsId),HttpStatus.OK);
//		}
//		catch (Exception e) {
//		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//		    }
//	}
	
	//save BimInstance
	@PostMapping(value = {"", "/"}, produces = "application/json", consumes = "application/json")
	public ResponseEntity<APIResponse> createBimInstance(@Valid @RequestBody BimInstance bimInstance, 
			BindingResult br) throws RequestValidationException, ResourceNotFoundException {
		
		try {
			try {
				BimInstance bimCheck = bimInstanceService.getBimInstanceById(bimInstance.getId());
				if(bimCheck != null) throw new RequestValidationException(
						Constant.ON_CREATE_DUP_ERROR);
			} catch (ResourceNotFoundException rnfx) {
				bimInstance.setId(0);
			}
			Bim bim = bimService.getBimById(bimInstance.getBim_id());
			bimInstance.setBim(bim);
			
			BimInstance bimInstance1 = bimInstanceService.createBimInstance(bimInstance);
			APIResponse resp = new APIResponse();
			return new ResponseEntity<>(resp, HttpStatus.CREATED);
		}
		catch (ResourceNotFoundException rnfx) {
			throw rnfx;
		}
		catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	}
	
	//update BimInstance
	@PutMapping(value = "/{id}", produces = "application/json", consumes = "application/json")
	public ResponseEntity<APIResponse> updateBimInstance(@PathVariable(value = "id") String id, 
			@Valid @RequestBody BimInstance bimInstanceDetail,
			BindingResult br) throws ResourceNotFoundException, RequestValidationException {
		
		try {
			
			Long bInsId = Long.parseLong(id);
			BimInstance bimInst = bimInstanceService.getBimInstanceById(bInsId);
			Bim bim = bimService.getBimById(bimInstanceDetail.getBim_id());
			bimInstanceDetail.setBim(bim);
			
			bimInstanceService.updateBimInstance(bInsId, bimInstanceDetail);
			
			APIResponse resp = new APIResponse(Constant.Entities.BimInstance);
			return new ResponseEntity<>(resp, HttpStatus.OK);
		}
		catch (NumberFormatException nfe) {
			throw new RequestValidationException(
					String.format(Constant.ON_PUT_INPUT_ERROR, Constant.Entities.BimInstance));
		}
		catch (ResourceNotFoundException rnf) {
			throw rnf;
		}
		catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	}
	
	//delete BimInstance
	@DeleteMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<Bim> deleteBimInstance(@PathVariable(value = "id") String id
			) throws ResourceNotFoundException, RequestValidationException {
		
		try {
			Long bInsId = Long.parseLong(id);
			bimInstanceService.getBimInstanceById(bInsId);
			
			bimInstanceService.deleteBimInstance(bInsId);
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
