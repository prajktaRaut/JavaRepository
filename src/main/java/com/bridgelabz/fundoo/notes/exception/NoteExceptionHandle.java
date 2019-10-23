package com.bridgelabz.fundoo.notes.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.bridgelabz.fundoo.exception.ExceptionResponse;

@ControllerAdvice
public class NoteExceptionHandle {

	@ExceptionHandler(NoteDoesNotExistException.class)
	public ResponseEntity<ExceptionResponse> getresponse(NoteDoesNotExistException exception)
	{
		ExceptionResponse response=new ExceptionResponse(exception.getMessage(), HttpStatus.NOT_FOUND.value());
		
		return new ResponseEntity<ExceptionResponse>(response, HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(IdDoesNotExistException.class)
	public ResponseEntity<ExceptionResponse> getexception(IdDoesNotExistException exception)
	{
		ExceptionResponse response=new ExceptionResponse(exception.getMessage(), HttpStatus.NOT_FOUND.value());
		
		return new ResponseEntity<ExceptionResponse>(response, HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(EmptyFieldException.class)
	public ResponseEntity<ExceptionResponse> getexception(EmptyFieldException exception)
	{
		ExceptionResponse response=new ExceptionResponse(exception.getMessage(), HttpStatus.BAD_REQUEST.value());
		
		return new ResponseEntity<ExceptionResponse>(response, HttpStatus.BAD_REQUEST);
		
	}
	
}
