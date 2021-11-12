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
import com.example.demo.repository.BimRepository;
import com.example.demo.service.BimService;

@RestController
@RequestMapping("/api/v1/bim")
public class BimController {
	
	@Autowired
	private  BimRepository bimRepository;
	
	@Autowired
	private BimService bimService;
	
	public BimController(BimService bimService) {
		this.bimService = bimService;
	}
	
	//get bim
		@GetMapping(value = "/listAll", produces = "application/json", consumes = "application/json")
		public ResponseEntity<List<Bim>> getAllBimDetail(){
			try {
				List<Bim> bim = bimService.getAllBimDetail();				
				return new ResponseEntity<>(bim, HttpStatus.OK);
			}
			catch (Exception e) {
			      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			    }
		}
		
		//get bim by id
		@GetMapping(value = "/{id}", produces = "application/json", consumes = "application/json")
		public ResponseEntity<Bim> getBimById(@PathVariable(value = "id") Long bimId)
			throws ResourceNotFoundException {
			try {
				return new ResponseEntity<>(bimService.getBimById(bimId),HttpStatus.OK);
			}
			catch (Exception e) {
			      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			    }
		}
		
		//save bim
		@PostMapping(value = "/create", produces = "application/json", consumes = "application/json")
		public ResponseEntity<Bim> createBim(@RequestBody Bim bim) {
			try {
				Bim bim1 = bimService.createBim(bim);
				return new ResponseEntity<>(bim1,HttpStatus.CREATED);
			}
			catch (Exception e) {
			      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			    }
		}
		
		//update bim
		@PutMapping(value = "/update/{id}", produces = "application/json", consumes = "application/json")
		public ResponseEntity<Bim> updateBim(@PathVariable(value = "id") Long bimId, @Validated @RequestBody Bim bimDetail) throws ResourceNotFoundException{			
			try {
				bimService.updateBim(bimId, bimDetail);
				return new ResponseEntity<>(HttpStatus.OK);
			}
			catch (Exception e) {
			      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			    }			
		}
		
		//delete bim
		@DeleteMapping(value = "/delete/{id}", produces = "application/json", consumes = "application/json")
		public ResponseEntity<Bim> deleteBim(@PathVariable(value = "id") Long bimId) throws ResourceNotFoundException {
			try {
				bimService.deleteBim(bimId);
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			catch (Exception e) {
			      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			    }			
		}

}
