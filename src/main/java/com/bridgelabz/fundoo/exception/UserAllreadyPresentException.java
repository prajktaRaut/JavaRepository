package com.bridgelabz.fundoo.exception;

public class UserAllreadyPresentException extends RuntimeException{

	public UserAllreadyPresentException() {
		
		super();
	}
	
	public UserAllreadyPresentException(String message)
	{
		super(message);
	}
}
