package com.bridgelabz.fundoo.notes.exception;

public class NoteDoesNotExistException extends RuntimeException {

	public NoteDoesNotExistException() {
	
		super();
	}
	
	public NoteDoesNotExistException(String message) {
		
		super(message);
	}
}
