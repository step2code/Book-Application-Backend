package com.book.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.book.model.User;
import com.book.service.IUserService;


@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

	@Autowired
	private IUserService service;
	
	@PostMapping("/register")
	public ResponseEntity<User> saveUser(@RequestBody User user){
		return new ResponseEntity<User>(service.saveUser(user),HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<User> loginUser(@RequestBody User user){
		return new ResponseEntity<User>(service.checkUserByUsernameAndPassword(user.getUsername(), user.getPassword()),HttpStatus.OK);
	}
	
	@GetMapping("/all-users")
	public ResponseEntity<List<User>> getAllUsers(){
		return new ResponseEntity<List<User>>(service.getAlluser(),HttpStatus.OK);
	}
	
	@GetMapping("/{username}")
	public ResponseEntity<User> getByUsername(@PathVariable String username){
		return new ResponseEntity<User>(service.getUserByUsername(username),HttpStatus.OK);
	}
	
	@PutMapping("/update-username/{id}/{username}")
	public ResponseEntity<User> updateUsername(@PathVariable Integer id,@PathVariable String username){
		return new ResponseEntity<User>(service.updateUsername(id,username),HttpStatus.OK);
	}
	
	@PutMapping("/update-passowrd")
	public ResponseEntity<User> updateUserPassword(@RequestBody User user){
		return new ResponseEntity<User>(service.updatePassword(user),HttpStatus.OK);
	}
}
