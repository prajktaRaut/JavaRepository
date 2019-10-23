package com.bridgelabz.fundoo.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class SendMailToUser {

	@Autowired
	private JavaMailSender javamailsender;
	
	public void sendmail(String to, String subject,String body)
	{
		SimpleMailMessage simplemailmessage= new SimpleMailMessage();
		
		simplemailmessage.setFrom("prajktasraut16@gmail.com");
		simplemailmessage.setTo(to);
		simplemailmessage.setSubject(subject);
		simplemailmessage.setText(body);

		javamailsender.send(simplemailmessage);
		
	}
	
	
}
