package com.bridgelabz.fundoo.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpStatusCodeException;

@ControllerAdvice
public class ExceptionHandlerControllerAdvice {


	@ExceptionHandler(UserDoesNotExistException.class)
	public ResponseEntity<ExceptionResponse> existresponse(UserDoesNotExistException exception) {
		ExceptionResponse exceptionresponse = new ExceptionResponse(exception.getMessage(),
				HttpStatus.UNAUTHORIZED.value());

		return new ResponseEntity<ExceptionResponse>(exceptionresponse, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(UserAllreadyPresentException.class)
	public ResponseEntity<ExceptionResponse> presentresponse(UserAllreadyPresentException exception) {
		ExceptionResponse exceptionresponse = new ExceptionResponse(exception.getMessage(),
				HttpStatus.ALREADY_REPORTED.value());

		return new ResponseEntity<ExceptionResponse>(exceptionresponse, HttpStatus.ALREADY_REPORTED);
	}

	@ExceptionHandler(RessetPasswordException.class)
	public ResponseEntity<ExceptionResponse> resetpasswordresponse(RessetPasswordException exception) {
		ExceptionResponse exceptionresponse = new ExceptionResponse(exception.getMessage(),
				HttpStatus.NOT_MODIFIED.value());

		return new ResponseEntity<ExceptionResponse>(exceptionresponse, HttpStatus.NOT_MODIFIED);
	}

	@ExceptionHandler(InvalidEmailException.class)
	public ResponseEntity<ExceptionResponse> emailresponse(InvalidEmailException exception) {
		ExceptionResponse exceptionresponse = new ExceptionResponse(exception.getMessage(),
				HttpStatus.NOT_FOUND.value());

		return new ResponseEntity<ExceptionResponse>(exceptionresponse, HttpStatus.NOT_FOUND);
	}
	

	/*
	 * @ExceptionHandler(UserDoesNotExistException.class)
	 * 
	 * @ResponseStatus(value = HttpStatus.NOT_FOUND) public @ResponseBody
	 * ExceptionResponse getResponse(UserDoesNotExistException exception,
	 * HttpServletRequest request) { ExceptionResponse response = new
	 * ExceptionResponse();
	 * 
	 * response.setMessage(exception.getMessage());
	 * //response.setRequested_uri(request.getRequestURI());
	 * 
	 * return response; }
	 */

	/*
	 * @ExceptionHandler(UserPresentException.class)
	 * 
	 * @ResponseStatus(value = HttpStatus.BAD_REQUEST) public @ResponseBody
	 * ExceptionResponse getResult(UserPresentException exception,
	 * HttpServletRequest request) { ExceptionResponse response= new
	 * ExceptionResponse();
	 * 
	 * response.setMessage(exception.getMessage());
	 * response.setRequested_uri(request.getRequestURI());
	 * 
	 * return response; }
	 */
}
