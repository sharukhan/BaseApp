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
import com.example.demo.model.BimInstance;
import com.example.demo.repository.BimInstanceRepository;

@RestController
@RequestMapping("/api/v1/bimInstance")
public class BimInstanceController {
	
	@Autowired
	private BimInstanceRepository bimInstanceRepository;
	
	
	//get All BimInstance
	@GetMapping("/listAllInstance")
	public List<BimInstance> getAllBimInstanceDetail(){
		return this.bimInstanceRepository.findAll();
	}
	
	//get BimInstance by id
	@GetMapping("/Detail/{id}")
	public ResponseEntity<BimInstance> getBimInstanceById(@PathVariable(value = "id") Long bInsId)
		throws ResourceNotFoundException {
		BimInstance bimInstance = bimInstanceRepository.findById(bInsId).orElseThrow(() -> new ResourceNotFoundException("Bim Insatnce not found:" + bInsId));
		return ResponseEntity.ok().body(bimInstance);
	}
	
	//save BimInstance
	@PostMapping("/createInstance")
	public BimInstance createBimInstance(@RequestBody BimInstance bimInstance) {
		return this.bimInstanceRepository.save(bimInstance);
	}
	
	//update BimInstance
	@PutMapping("/updateInstance/{id}")
	public ResponseEntity<BimInstance> updateBimInstance(@PathVariable(value = "id") Long bInsId, @Validated @RequestBody BimInstance bimInstanceDetail) throws ResourceNotFoundException{
		
		BimInstance bimInstance = bimInstanceRepository.findById(bInsId).orElseThrow(() -> new ResourceNotFoundException("Bim Insatnce not found:" + bInsId));
		bimInstance.setInsClientId(bimInstanceDetail.getInsClientId());
		bimInstance.setInsSupplierId(bimInstanceDetail.getInsSupplierId());
		bimInstance.setBimId(bimInstanceDetail.getBimId());
		bimInstance.setStatus(bimInstanceDetail.getStatus());
		return ResponseEntity.ok(this.bimInstanceRepository.save(bimInstance));
		
	}
	
	//delete BimInstance
	@DeleteMapping("/deleteInstance/{id}")
	public Map<String, Boolean> deleteBimInstance(@PathVariable(value = "id") Long bInsId) throws ResourceNotFoundException {
		
		BimInstance bimInstance = bimInstanceRepository.findById(bInsId).orElseThrow(() -> new ResourceNotFoundException("Bim Insatnce not found:" + bInsId));
		this.bimInstanceRepository.delete(bimInstance);
		
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		
		return response;
		
	}

}
