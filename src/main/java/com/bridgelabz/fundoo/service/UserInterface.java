package com.bridgelabz.fundoo.service;

import com.bridgelabz.fundoo.dto.ForgetPasswordDto;
import com.bridgelabz.fundoo.dto.LoginDto;
import com.bridgelabz.fundoo.dto.UserDto;
import com.bridgelabz.fundoo.model.UserModel;

public interface UserInterface {

	public void saveuser(UserModel user);
	
	public UserModel getuser(int id);
	
	public String register(UserDto userdto);
	
	public String login(LoginDto logindto) throws Exception;
	
	public String deleteuser(int id);
	
	public String resetpassword(Integer id,ForgetPasswordDto forgetpassworddto);
	
}
