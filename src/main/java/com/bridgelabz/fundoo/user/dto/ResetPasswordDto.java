package com.bridgelabz.fundoo.user.dto;

import javax.validation.constraints.Size;

import org.springframework.lang.NonNull;

public class ResetPasswordDto {

	@NonNull
	@Size(min = 10, max = 25, message = "Password should be greater than 10 character and less than 25 character")
	private String password;

	@NonNull
	@Size(min = 10, max = 25, message = "Password should be greater than 10 character and less than 25 character")
	private String confirmpassword;

	public ResetPasswordDto(
			@Size(min = 10, max = 25, message = "Password should be greater than 10 character and less than 25 character") String password,
			@Size(min = 10, max = 25, message = "Password should be greater than 10 character and less than 25 character") String confirmpassword) {
		super();
		this.password = password;
		this.confirmpassword = confirmpassword;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmpassword() {
		return confirmpassword;
	}

	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
	}

	@Override
	public String toString() {
		return "ForgetPasswordDto [password=" + password + ", confirmpassword=" + confirmpassword + "]";
	}
	
	
}
