package com.bridgelabz.fundoo.exception;

public class ExceptionResponse {

	String message;
	int statuscode;
	
	
	public ExceptionResponse() {
		super();
	}

	

	public ExceptionResponse(String message, int statuscode) {
		super();
		this.message = message;
		this.statuscode = statuscode;
	}



	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public int getStatuscode() {
		return statuscode;
	}


	public void setStatuscode(int statuscode) {
		this.statuscode = statuscode;
	}


	@Override
	public String toString() {
		return "ExceptionResponse [message=" + message + ", statuscode=" + statuscode + "]";
	}

	

	



	
	
	
}
