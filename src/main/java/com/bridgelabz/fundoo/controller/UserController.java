package com.bridgelabz.fundoo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoo.dto.ForgetPasswordDto;
import com.bridgelabz.fundoo.dto.LoginDto;
import com.bridgelabz.fundoo.dto.UserDto;
import com.bridgelabz.fundoo.model.UserModel;
import com.bridgelabz.fundoo.repository.UserRepository;
import com.bridgelabz.fundoo.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserService userservice;

	  
	 
	/*
	  @PostMapping("register") public String adduser(@RequestParam String
	  username,@RequestParam String firstname, @RequestParam String
	  lastname,@RequestParam String email_id, @RequestParam String
	  mobilenumber,@RequestParam String password)
	  { 
		  UserModel user= new
	  UserModel(username, firstname, lastname, email_id, mobilenumber, password);
	  
	  userservice.saveuser(user);
	  
	  return "register suceessfully....."; }*/
	 

	
	
	 /* @PostMapping("user") public String adduser(UserModel user) {
	  userservice.saveuser(user);
	  
	  return "Register Successfully....."; }
	 
	  
	
	  @GetMapping("getuser/{id}") public String getuser(@PathVariable Integer id) {
	  UserModel user = userservice.getuser(id);
	  
	  return "user found " + user;
	  
	  }*/
	 
	 

	@PostMapping("register")
	public String registeruser(@RequestBody @Valid UserDto userdto) {
		String register = userservice.register(userdto);

		return register;

	}

	@PostMapping("login")
	public String userlogin(@RequestBody @Valid LoginDto logindto) throws Exception
	{
		String login=userservice.login(logindto);
		
		return login;
		
	}
	
	@DeleteMapping("delete/{id}")
	public String deleteuser(@PathVariable Integer id)
	{
		String delete=userservice.deleteuser(id);
		
		return delete;
		
	}
	
	@PostMapping("reset/{id}")
	public String resetpassword(@PathVariable Integer id, @RequestBody @Valid ForgetPasswordDto forgetpassworddto)
	{
		String status=userservice.resetpassword(id, forgetpassworddto);
		
		return status;
		
	}
	
}
