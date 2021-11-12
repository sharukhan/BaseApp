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
import com.example.demo.model.BimInstance;
import com.example.demo.repository.BimInstanceRepository;
import com.example.demo.service.BimInstanceService;

@RestController
@RequestMapping("/api/v1/bimInstance")
public class BimInstanceController {
	
	@Autowired
	private BimInstanceRepository bimInstanceRepository;
	
	@Autowired
	private BimInstanceService bimInstanceService;
	
	//get All BimInstance
	@GetMapping(value = "/listAll", produces = "application/json", consumes = "application/json")
	public ResponseEntity<List<BimInstance>> getAllBimInstanceDetail(){
		try {
			List<BimInstance> bimInstance = bimInstanceService.getAllBimInstanceDetail();				
			return new ResponseEntity<>(bimInstance, HttpStatus.OK);
		}
		catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	}
	
	//get BimInstance by id
	@GetMapping(value = "/{id}", produces = "application/json", consumes = "application/json")
	public ResponseEntity<BimInstance> getBimInstanceById(@PathVariable(value = "id") Long bInsId)
		throws ResourceNotFoundException {
		try {
			return new ResponseEntity<>(bimInstanceService.getBimInstanceById(bInsId),HttpStatus.OK);
		}
		catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	}
	
	//save BimInstance
	@PostMapping(value = "/create", produces = "application/json", consumes = "application/json")
	public ResponseEntity<BimInstance> createBimInstance(@RequestBody BimInstance bimInstance) {
		try {
			BimInstance bimInstance1 = bimInstanceService.createBimInstance(bimInstance);
			return new ResponseEntity<>(bimInstance1,HttpStatus.CREATED);
		}
		catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	}
	
	//update BimInstance
	@PutMapping(value = "/update/{id}", produces = "application/json", consumes = "application/json")
	public ResponseEntity<BimInstance> updateBimInstance(@PathVariable(value = "id") Long bInsId, @Validated @RequestBody BimInstance bimInstanceDetail) throws ResourceNotFoundException{
		
		try {
			bimInstanceService.updateBimInstance(bInsId, bimInstanceDetail);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	}
	
	//delete BimInstance
	@DeleteMapping(value = "/delete/{id}", produces = "application/json", consumes = "application/json")
	public ResponseEntity<Bim> deleteBimInstance(@PathVariable(value = "id") Long bInsId) throws ResourceNotFoundException {
		
		try {
			bimInstanceService.deleteBimInstance(bInsId);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
		
	}

}
