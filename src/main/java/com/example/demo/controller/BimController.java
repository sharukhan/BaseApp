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
import com.example.demo.repository.BimRepository;
import com.example.demo.service.BimService;

@RestController
@RequestMapping("/api/v1/bim")
public class BimController {
	
	@Autowired
	private  BimRepository bimRepository;
	
	@Autowired
	private BimService bimService;
	
	//get bim
		@GetMapping("/listAll")
		public List<Bim> getAllBimDetail(){
			List<Bim> bimDetail = new ArrayList<>();
			try {
				bimDetail = bimService.getAllBimDetail();
			}
			catch (Exception e) {
				System.out.println(e);
			}
			
			return bimDetail;
		}
		
		//get bim by id
		@GetMapping("/detail/{id}")
		public Bim getBimUserById(@PathVariable(value = "id") Long bimId)
			throws ResourceNotFoundException {
			Bim bim = new Bim();
			try {
				bim = bimService.getBimUserById(bimId);
			}
			catch (Exception e) {
				System.out.println(e);
			}
			
			return bim;
		}
		
		//save bim
		@PostMapping("/create")
		public Bim createBimUser(@RequestBody Bim bim) {
			try {
				bimService.createBimUser(bim);
			}
			catch (Exception e) {
				System.out.println(e);
			}
			return bim;
		}
		
		//update bim
		@PutMapping("/update/{id}")
		public ResponseEntity<Bim> updateBimUser(@PathVariable(value = "id") Long bimId, @Validated @RequestBody Bim bimDetail) throws ResourceNotFoundException{
			
			try {
				bimService.updateBimUser(bimId, bimDetail);
			}
			catch (Exception e) {
				System.out.println(e);
			}
			return null;
			
		}
		
		//delete bim
		@DeleteMapping("/delete/{id}")
		public void deleteBimUser(@PathVariable(value = "id") Long bimId) throws ResourceNotFoundException {
			
			try {
				bimService.deleteBimUser(bimId);
			}
			catch (Exception e) {
				System.out.println(e);
			}
			
		}

}
