package com.bridgelabz.fundoo.user.service;


import com.bridgelabz.fundoo.user.dto.LoginDto;
import com.bridgelabz.fundoo.user.dto.ResetPasswordDto;
import com.bridgelabz.fundoo.user.dto.UserDto;
import com.bridgelabz.fundoo.exception.RessetPasswordException;
import com.bridgelabz.fundoo.exception.UserAllreadyPresentException;
import com.bridgelabz.fundoo.exception.UserDoesNotExistException;
import com.bridgelabz.fundoo.exception.UserverifyException;
import com.bridgelabz.fundoo.user.model.UserModel;
import com.bridgelabz.fundoo.utility.Response;

public interface UserServiceInterface {

	public void saveuser(UserModel user);
	
	public UserModel getuser(long id);
	
	public Response register(UserDto userdto) throws UserAllreadyPresentException;
	
	public Response login(LoginDto logindto) throws UserDoesNotExistException;
	
	public String deleteuser(long id);
	
	public Response resetpassword(String token, ResetPasswordDto resetpassworddto) throws RessetPasswordException;
	
	public Response forgetpassword(String email);
	
	public Response verifyuser(String token) throws UserverifyException;
}
