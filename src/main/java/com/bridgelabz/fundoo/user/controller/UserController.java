package com.bridgelabz.fundoo.user.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoo.user.dto.LoginDto;
import com.bridgelabz.fundoo.user.dto.ResetPasswordDto;
import com.bridgelabz.fundoo.user.dto.UserDto;
import com.bridgelabz.fundoo.user.service.UserServiceImplement;
import com.bridgelabz.fundoo.utility.Response;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserServiceImplement userservice;

	@PostMapping("/register")
	public ResponseEntity<Response> registeruser(@RequestBody @Valid UserDto userdto) {
	
		Response response=userservice.register(userdto);

		return new ResponseEntity<Response>(response, HttpStatus.CONFLICT);

	}

	@PostMapping("/login")
	public ResponseEntity<Response> userlogin(@RequestBody @Valid LoginDto logindto) 
	{
		Response response= userservice.login(logindto);
		
		return new ResponseEntity<Response>(response, HttpStatus.CONFLICT);
		
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteuser(@PathVariable Integer id)
	{
		String delete=userservice.deleteuser(id);
		
		return delete;
		
	}
	
	@PostMapping("/reset/{token}")
	public ResponseEntity<Response> resetpassword(@PathVariable String token, @RequestBody @Valid ResetPasswordDto resetpassworddto)
	{
		
		Response response=userservice.resetpassword(token, resetpassworddto);
		
		return new ResponseEntity<Response>(response, HttpStatus.CONFLICT);
	
		
	}
	
	@PutMapping("/forget")
	public ResponseEntity<Response> forgetpassword(@RequestParam String email)
	{
		Response response=userservice.forgetpassword(email);
		
		return new ResponseEntity<Response>(response, HttpStatus.CONFLICT);
		
		
	}
	
	@PutMapping("/verifyuser/{token}")
	public ResponseEntity<Response> verifyuser(@PathVariable String token)
	{
		Response response=userservice.verifyuser(token);
		
		return new ResponseEntity<Response>(response, HttpStatus.CONFLICT);
	}
	
}
