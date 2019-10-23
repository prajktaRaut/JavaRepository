package com.bridgelabz.fundoo.user.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoo.exception.InvalidEmailException;
import com.bridgelabz.fundoo.exception.RessetPasswordException;
import com.bridgelabz.fundoo.exception.UserAllreadyPresentException;
import com.bridgelabz.fundoo.exception.UserDoesNotExistException;
import com.bridgelabz.fundoo.exception.UserverifyException;
import com.bridgelabz.fundoo.user.dto.LoginDto;
import com.bridgelabz.fundoo.user.dto.ResetPasswordDto;
import com.bridgelabz.fundoo.user.dto.UserDto;
import com.bridgelabz.fundoo.user.model.UserModel;
import com.bridgelabz.fundoo.user.repository.UserRepository;
import com.bridgelabz.fundoo.utility.Response;
import com.bridgelabz.fundoo.utility.SendMailToUser;
import com.bridgelabz.fundoo.utility.TokenUtil;

@Service
@PropertySource("classpath:errormessage.properties")
public class UserServiceImplement implements UserServiceInterface {

	@Autowired
	private UserRepository userrepository;

	@Autowired
	private ModelMapper modelmapper;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private BCryptPasswordEncoder bcryptpasswordencoder;

	@Autowired
	private TokenUtil tokenutil;

	@Autowired
	private SendMailToUser sendmailtouser;

	@Autowired
	private Environment environment;

	@Override
	public void saveuser(UserModel user) {

		userrepository.save(user);

	}

	@Override
	public UserModel getuser(long id) {

		Optional<UserModel> user = userrepository.findById(id);
		return user.get();
	}

	@Override
	public Response register(UserDto userdto) throws UserAllreadyPresentException {

		Optional<UserModel> checkuser = userrepository.findByEmail(userdto.getEmail());

		if (!checkuser.isPresent()) {
			UserModel user = modelmapper.map(userdto, UserModel.class);

			String password = passwordEncoder.encode(userdto.getPassword());

			user.setPassword(password);
			user.setRegisterdate(LocalDateTime.now());
			user.setUpdatedate(LocalDateTime.now());
		//	user.setIsverified(true);

			userrepository.save(user);

			String token = tokenutil.createtoken(user.getId());

			String to = userdto.getEmail();
			String subject = "TO VERIFY USER";
			String body = "VERIFYING USER " + "http://localhost:8083/user/verifyuser/" + token;

			sendmailtouser.sendmail(to, subject, body);

			return new Response(HttpStatus.ACCEPTED.value(), environment.getProperty("status.register.success"), null);

		} else {

			// return new Response(400, "USER ALLREADY REGISTERED WITH THIS EMAIL_ID",null);

			throw new UserAllreadyPresentException(environment.getProperty("status.register.userpresentornot"));

		}

	}

	@Override
	public Response login(LoginDto logindto) throws UserDoesNotExistException {

		Optional<UserModel> checkuser = userrepository.findByEmail(logindto.getEmail());

		if (!checkuser.isPresent()) {

			throw new UserDoesNotExistException(environment.getProperty("status.login.userexist"));
		} else {
			if ((passwordEncoder.matches(logindto.getPassword(), checkuser.get().getPassword()))) {
				throw new UserDoesNotExistException(environment.getProperty("status.login.success"));
			} else {
				return new Response(HttpStatus.BAD_REQUEST.value(),
						environment.getProperty("status.login.incorrectpassword"), null);
			}
		}

	}


	@Override
	public Response resetpassword(String token, ResetPasswordDto resetpassworddto) throws RessetPasswordException {

		long id = tokenutil.decodetoken(token);

		Optional<UserModel> user = userrepository.findById(id);

		if (user.get().isIsverified()) {

			if (resetpassworddto.getPassword().equals(resetpassworddto.getConfirmpassword())) {

				return new Response(HttpStatus.ACCEPTED.value(), environment.getProperty("status.login.resetpassword"),
						token);

			} else {
				return new Response(HttpStatus.BAD_REQUEST.value(),
						environment.getProperty("status.login.passwordmatched"), null);

			}
		}
		throw new RessetPasswordException(environment.getProperty("status.login.passwordmatched"));

	}

	@Override
	public Response forgetpassword(String email) {

		Optional<UserModel> user = userrepository.findByEmail(email);

		if (user.get().isIsverified()) {

			String token = tokenutil.createtoken(user.get().getId());

			String to = email;
			String subject = "REQUEST FOR RESETTING PASSWORD";
			String body = "RESETTING PASSWORD LINK" + "http://localhost:8083/reset/" + token;

			sendmailtouser.sendmail(to, subject, body);

			return new Response(HttpStatus.ACCEPTED.value(), environment.getProperty("status.login.resetpassword"),
					null);

		}

		throw new InvalidEmailException(environment.getProperty("status.login.resetpasswordfailed"));

	}

	@Override
	public Response verifyuser(String token) throws UserverifyException {

		long id = tokenutil.decodetoken(token);

		Optional<UserModel> verifyuser = userrepository.findById(id);

		if (verifyuser.isPresent()) {
			verifyuser.get().setIsverified(true);

			userrepository.save(verifyuser.get());

			return new Response(HttpStatus.ACCEPTED.value(), environment.getProperty("status.register.userverify"),
					token);

		}

		throw new UserverifyException(environment.getProperty("status.register.userverificationfailed"));

	}

	@Override
	public String deleteuser(long id) {
		
		userrepository.deleteById(id);
		
		return "user deleted successfully.....";
	}

}
