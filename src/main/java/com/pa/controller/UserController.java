package com.pa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pa.entity.User;
import com.pa.request.UpdateUserRequest;
import com.pa.service.UserService;

@RestController
@RequestMapping(path = "/api/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	//API return all users 
	@GetMapping(path = "/getall")
	public ResponseEntity<List<User>> getAll() {
	    return new ResponseEntity<List<User>>(userService.getAll(), HttpStatus.OK);
	}
	
	//API update user
	@PutMapping(path = "/update")
	public ResponseEntity<User> updateUser(@RequestParam("id") int id,
	        @RequestBody UpdateUserRequest updateUserRequest) {
	    User updatedUser = userService.update(id, updateUserRequest.getUserDto(), updateUserRequest.getDataLoginDto());
	    
	    if (updatedUser != null) {
	        return new ResponseEntity<User>(updatedUser, HttpStatus.OK);
	    } else {
	        return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
	    }
	}
	
	// API remove user
	@DeleteMapping(path = "/remove")
	public ResponseEntity<Boolean> removeUser(@RequestParam("id") int id) {
	    boolean isDeleted = userService.delete(id);
	    return new ResponseEntity<>(isDeleted, HttpStatus.OK);
	}

}
