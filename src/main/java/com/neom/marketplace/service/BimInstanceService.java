package com.neom.marketplace.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.neom.marketplace.exception.ResourceNotFoundException;
import com.neom.marketplace.model.Bim;
import com.neom.marketplace.model.BimInstance;
import com.neom.marketplace.repository.BimInstanceRepository;

@Service
public class BimInstanceService {
	
	@Autowired
	BimInstanceRepository bimInstanceRepository;
	
	
	public List<BimInstance> getAllBimInstanceDetail(){
		List<BimInstance> bimInstance = new ArrayList<>();
		bimInstanceRepository.findAll().forEach(bimInstance::add);
		return bimInstance;
	}
	
	public BimInstance getBimInstanceById(Long bInsId)
		throws ResourceNotFoundException {
		BimInstance bimInstance = bimInstanceRepository.findById(bInsId).orElseThrow(() -> new ResourceNotFoundException("Bim Insatnce not found:" + bInsId));
		return bimInstance;
	}
	

	public BimInstance createBimInstance(BimInstance bimInstance) {
		return this.bimInstanceRepository.save(bimInstance);
	}
	
	public BimInstance updateBimInstance(Long bInsId,BimInstance bimInstanceDetail) throws ResourceNotFoundException{		
		BimInstance bimInstance = bimInstanceRepository.findById(bInsId).orElseThrow(() -> new ResourceNotFoundException("Bim Insatnce not found:" + bInsId));
		bimInstance.setClient_Id(bimInstanceDetail.getClient_Id());
		bimInstance.setSupplier_Id(bimInstanceDetail.getSupplier_Id());
		bimInstance.setStatus(bimInstanceDetail.getStatus());
		return this.bimInstanceRepository.save(bimInstance);		
	}
	
	public void deleteBimInstance(Long bInsId) throws ResourceNotFoundException {		
		BimInstance bimInstance = bimInstanceRepository.findById(bInsId).orElseThrow(() -> new ResourceNotFoundException("Bim Insatnce not found:" + bInsId));
		this.bimInstanceRepository.delete(bimInstance);	
	}

}
