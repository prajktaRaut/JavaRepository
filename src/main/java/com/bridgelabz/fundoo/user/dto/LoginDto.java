package com.bridgelabz.fundoo.user.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.lang.NonNull;


public class LoginDto {

	@NonNull
	@Email
	@Size(min = 10, max = 30, message = "email_id should be greater than 10 and less than 30")
	private String email;

	@NonNull
	@Size(min = 8, max = 20, message = "password length should be greater than 8 and less than 20")
	private String password;

	public LoginDto(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "LoginDto [email=" + email + ", password=" + password + "]";
	}

	
	
}
