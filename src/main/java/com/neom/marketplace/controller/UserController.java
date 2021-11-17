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
import com.neom.marketplace.model.User;
import com.neom.marketplace.repository.UserRepository;
import com.neom.marketplace.service.UserService;

@RestController
@RequestMapping("/api/v1/user")
@Validated
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;
	
	//get user 
	@GetMapping(value = {"", "/"}, produces = "application/json", consumes = "application/json")
	public ResponseEntity<List<User>> getAllUser(){
		try {
			List<User> user = userService.getAllUser();				
			return new ResponseEntity<>(user, HttpStatus.OK);
		}
		catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	}
	
	
	//get user by id
//	@GetMapping(value = "/{id}" ,produces = "application/json", consumes = "application/json")
//	public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long userId)
//		throws ResourceNotFoundException {
//		try {
//			return new ResponseEntity<>(userService.getUserById(userId),HttpStatus.OK);
//		}
//		catch (Exception e) {
//		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//		    }
//	}
	
	//save user
	@PostMapping(value = {"", "/"}, produces = "application/json", consumes = "application/json")
	public ResponseEntity<APIResponse> createUser(@Valid @RequestBody User user,
			BindingResult br) throws RequestValidationException {
		try {
			try {
				User userCheck = userService.getUserById(user.getId());
				if(userCheck != null) throw new RequestValidationException(
						Constant.ON_CREATE_DUP_ERROR);
			} catch (ResourceNotFoundException rnfx) {
				user.setId(0);
			}
			
			User user1 = userService.createUser(user);
			APIResponse resp = new APIResponse();
			return new ResponseEntity<>(resp, HttpStatus.CREATED);
		}
		catch(RequestValidationException rve) {
			throw rve;
		}
		catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	}
	
	//update user
	@PutMapping(value = "/{id}",produces = "application/json", consumes = "application/json")
	public ResponseEntity<APIResponse> updateUser(@PathVariable(value = "id") String id, 
			@Valid @RequestBody User userDetail,
			BindingResult br) throws RequestValidationException, ResourceNotFoundException {
		
		try {
			Long userId = Long.parseLong(id);
			userService.getUserById(userId);
			userService.updateUser(userId, userDetail);
			
			APIResponse resp = new APIResponse(Constant.Entities.User);
			return new ResponseEntity<>(resp, HttpStatus.OK);
		}
		catch (NumberFormatException nfe) {
			throw new RequestValidationException(
					String.format(Constant.ON_PUT_INPUT_ERROR, Constant.Entities.User));
		}
		catch (ResourceNotFoundException rnf) {
			throw rnf;
		}
		catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
		
	}
	//delete user
	@DeleteMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<User> deleteUser(
			@PathVariable(value = "id") String id) throws ResourceNotFoundException, RequestValidationException {
		
		try {
			Long userId = Long.parseLong(id);
			userService.getUserById(userId);
			userService.deleteUser(userId);
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
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
