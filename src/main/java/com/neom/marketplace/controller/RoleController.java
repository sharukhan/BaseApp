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
import com.neom.marketplace.model.Role;
import com.neom.marketplace.repository.UserRoleRepository;
import com.neom.marketplace.service.UserRoleService;

@RestController
@RequestMapping("/api/v1/role")
@Validated
public class RoleController {
	
	@Autowired
	private UserRoleRepository userRoleRepository;
	
	@Autowired
	private UserRoleService userRoleService;
	
	//get All BimInstance
		@GetMapping(value = {"", "/"}, produces = "application/json", consumes = "application/json")
		public ResponseEntity<List<Role>> getAllUserRoleDetail(){
			try {
				List<Role> userRole = userRoleService.getAllUserRole();				
				return new ResponseEntity<>(userRole, HttpStatus.OK);
			}
			catch (Exception e) {
			      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			    }
		}
		
		//get BimInstance by id
//		@GetMapping(value = "/{id}", produces = "application/json", consumes = "application/json")
//		public ResponseEntity<Role> getUserRoleById(@PathVariable(value = "id") Long userRoleId)
//			throws ResourceNotFoundException {
//			try {
//				return new ResponseEntity<>(userRoleService.getUserRoleById(userRoleId),HttpStatus.OK);
//			}
//			catch (Exception e) {
//			      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//			    }
//		}
		
		//save BimInstance
		@PostMapping(value = {"", "/"}, produces = "application/json", consumes = "application/json")
		public ResponseEntity<Role> createUserRole(@Valid @RequestBody Role userRole, 
				BindingResult br) throws RequestValidationException {
			
			try {
				try {
					Role roleCheck = userRoleService.getUserRoleById(userRole.getId());
					if(roleCheck != null) throw new RequestValidationException(
							Constant.ON_CREATE_DUP_ERROR);
				} catch (ResourceNotFoundException rnfx) {
					userRole.setId(0);
				}
				Role userRole1 = userRoleService.createUserRole(userRole);
				return new ResponseEntity<>(userRole1,HttpStatus.CREATED);
			}
			catch(RequestValidationException rve) {
				throw rve;
			}
			catch (Exception e) {
			      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			    }
		}
		
		//update BimInstance
		@PutMapping(value = "/{id}", produces = "application/json", consumes = "application/json")
		public ResponseEntity<APIResponse> updateUserRole(@PathVariable(value = "id") String id, 
				@Valid @RequestBody Role userRoleDetail,
				BindingResult br) throws ResourceNotFoundException, RequestValidationException {
			
			try {
				Long userRoleId = Long.parseLong(id);
				userRoleService.getUserRoleById(userRoleId);
				userRoleService.updateUserRole(userRoleId, userRoleDetail);
				
				APIResponse resp = new APIResponse(Constant.Entities.Role);
				return new ResponseEntity<>(resp, HttpStatus.OK);
			}
			catch (NumberFormatException nfe) {
				throw new RequestValidationException(
						String.format(Constant.ON_PUT_INPUT_ERROR, Constant.Entities.Role));
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
		public ResponseEntity<Role> deleteUserRole(
				@PathVariable(value = "id") String id) throws ResourceNotFoundException, RequestValidationException {			
			
			try {
				Long userRoleId = Long.parseLong(id);
				userRoleService.getUserRoleById(userRoleId);
				userRoleService.deleteUserRole(userRoleId);
				
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
