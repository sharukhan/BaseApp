package com.example.demo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Bim;
import com.example.demo.repository.BimRepository;

@Service
public class BimService {
	
	@Autowired
	BimRepository bimRepository;
	
	public List<Bim> getAllBimDetail(){
		List<Bim> bim = new ArrayList<>();
		bimRepository.findAll().forEach(bim::add);
		return bim;
	}
	
	public Bim getBimById(Long bimId)
			throws ResourceNotFoundException {
			Bim bim = bimRepository.findById(bimId).orElseThrow(() -> new ResourceNotFoundException("Bim not found:" + bimId));
			return bim;
		}
	
	public Bim createBim(Bim bim) {
		return this.bimRepository.save(bim);
	}
	
	public Bim updateBim(Long bimId, Bim bimDetail) throws ResourceNotFoundException{		
		Bim bim = bimRepository.findById(bimId).orElseThrow(() -> new ResourceNotFoundException("Bim not found:" + bimId));
		bim.setSupplierId(bimDetail.getSupplierId());
		bim.setTitle(bimDetail.getTitle());
		bim.setInsight_Url(bimDetail.getInsight_Url());
		bim.setBenefits_Url(bimDetail.getBenefits_Url());
		bim.setOne_Liner_Description(bimDetail.getOne_Liner_Description());
		bim.setRating(bimDetail.getRating());
		bim.setLayout_Type(bimDetail.getLayout_Type());
		bim.setStatus(bimDetail.getStatus());
		bim.setContent_Id(bimDetail.getContent_Id());
		bim.setShort_Description(bimDetail.getShort_Description());
		return this.bimRepository.save(bim);		
	}
	
	public void deleteBim(Long bimId) throws ResourceNotFoundException {		
		Bim bim = bimRepository.findById(bimId).orElseThrow(() -> new ResourceNotFoundException("Bim not found:" + bimId));
		this.bimRepository.delete(bim);		
	}

}
