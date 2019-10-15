package com.bridgelabz.fundoo.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoo.dto.ForgetPasswordDto;
import com.bridgelabz.fundoo.dto.LoginDto;
import com.bridgelabz.fundoo.dto.UserDto;
import com.bridgelabz.fundoo.model.UserModel;
import com.bridgelabz.fundoo.repository.UserRepository;


@Service
public class UserService implements UserInterface{

	@Autowired
	private UserRepository userrepository;
	
	@Autowired
	private ModelMapper modelmapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private BCryptPasswordEncoder bcryptpasswordencoder;
	
	@Override
	public void saveuser(UserModel user) {
		
		userrepository.save(user);
		
	}

	@Override
	public UserModel getuser(int id) {
		
		Optional<UserModel> user=userrepository.findById(id);
		
		return user.get();
	}

	@Override
	public String register(UserDto userdto) {
		
		UserModel user=modelmapper.map(userdto, UserModel.class);
		
		System.out.println("is both password are equal ? "+userdto.getPassword().equals(user.getPassword()));
		
		String password=bcryptpasswordencoder.encode(userdto.getPassword());
		
		user.setPassword(password);
		user.setRegisterdate(LocalDateTime.now());
		user.setUpdatedate(LocalDateTime.now());
		user.setIsverified(true);
		userrepository.save(user);
		
		return "User Register successfully.....";
	}

	@Override
	public String login(LoginDto logindto) throws Exception {
		
		UserModel checkuser=userrepository.findByEmail(logindto.getEmail()).orElseThrow(()->new Exception("Invalid username"));
		
		
		  String newpassword=bcryptpasswordencoder.encode(logindto.getPassword());
		  
		  //String originalpass=bcryptpasswordencoder.encode(checkuser.getPassword());
		 
		
		System.out.println("password stored inthe database"+checkuser.getPassword());
		System.out.println("new password is "+newpassword);
		
		System.out.println("password stored in the database"+checkuser.getPassword());
		
		System.out.println("password get by logindto"+logindto.getPassword());
		
		if((checkuser.getEmail().equals(logindto.getEmail()))&&(bcryptpasswordencoder.matches(logindto.getPassword(),checkuser.getPassword())))
		{
			return "Login successfully.....";
			
		}
		else if((!checkuser.getEmail().equals(logindto.getEmail())) && (bcryptpasswordencoder.matches(logindto.getPassword(),checkuser.getPassword())))
		{
			return "Login unsuccessful.....";
			
		}
		else
		{
			return "User does not exist";
		}
			
		
	}

	@Override
	public String deleteuser(int id) {
		
		userrepository.deleteById(id);
		
		return "user deleted successfully.....";
	}

	@Override
	public String resetpassword(Integer id,ForgetPasswordDto forgetpassworddto) {
	
	
		UserModel user=userrepository.getOne(id);
		
		if(forgetpassworddto.getPassword().equals(forgetpassworddto.getConfirmpassword()))
		{
			
			System.out.println("original password is"+forgetpassworddto.getPassword());
		
			String newpassword=passwordEncoder.encode(forgetpassworddto.getPassword());
			
			System.out.println("encoded password is"+newpassword);
			
			
			System.out.println("both password are "+(newpassword.equals(forgetpassworddto.getPassword())));
			user.setPassword(newpassword);
			
			userrepository.save(user);
			
			return "Reset password sucessfully.....";
			
		}
		else if(!forgetpassworddto.getPassword().equals(forgetpassworddto.getConfirmpassword()))
		{
			return "password and confirm password should be matched";
		}
		else
		{
			return "Reset password unsuccessful....";
		}
		
	
	}





}
