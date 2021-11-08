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
	@GetMapping("/listAllInstance")
	public List<BimInstance> getAllBimInstanceDetail(){
		List<BimInstance> bimInstance = new ArrayList<>();
		try {
			bimInstance = bimInstanceService.getAllBimInstanceDetail();
		}
		catch (Exception e) {
			System.out.println(e);
		}
		
		return bimInstance;
	}
	
	//get BimInstance by id
	@GetMapping("/Detail/{id}")
	public BimInstance getBimInstanceById(@PathVariable(value = "id") Long bInsId)
		throws ResourceNotFoundException {
		BimInstance bimInstance = new BimInstance();
			try {
				bimInstance = bimInstanceService.getBimInstanceById(bInsId);
			}
			catch (Exception e) {
				System.out.println(e);
			}
			
			return bimInstance;
	}
	
	//save BimInstance
	@PostMapping("/createInstance")
	public BimInstance createBimInstance(@RequestBody BimInstance bimInstance) {
		try {
			bimInstanceService.createBimInstance(bimInstance);
		}
		catch (Exception e) {
			System.out.println(e);
		}
		return bimInstance;
	}
	
	//update BimInstance
	@PutMapping("/updateInstance/{id}")
	public ResponseEntity<BimInstance> updateBimInstance(@PathVariable(value = "id") Long bInsId, @Validated @RequestBody BimInstance bimInstanceDetail) throws ResourceNotFoundException{
		
		try {
			bimInstanceService.updateBimInstance(bInsId, bimInstanceDetail);
		}
		catch (Exception e) {
			System.out.println(e);
		}
		return null;
		
	}
	
	//delete BimInstance
	@DeleteMapping("/deleteInstance/{id}")
	public void deleteBimInstance(@PathVariable(value = "id") Long bInsId) throws ResourceNotFoundException {
		
		try {
			bimInstanceService.deleteBimInstance(bInsId);
		}
		catch (Exception e) {
			System.out.println(e);
		}
		
	}

}
