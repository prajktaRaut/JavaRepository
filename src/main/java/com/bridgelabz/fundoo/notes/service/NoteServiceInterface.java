package com.bridgelabz.fundoo.notes.service;

import java.util.List;

import com.bridgelabz.fundoo.notes.dto.NoteDto;
import com.bridgelabz.fundoo.notes.exception.EmptyFieldException;
import com.bridgelabz.fundoo.notes.exception.IdDoesNotExistException;
import com.bridgelabz.fundoo.notes.exception.NoteDoesNotExistException;
import com.bridgelabz.fundoo.notes.model.NoteModel;
import com.bridgelabz.fundoo.utility.Response;

public interface NoteServiceInterface {

	public Response addnote(NoteDto notedto) throws EmptyFieldException;
	
	public Response deletenote(long id)throws IdDoesNotExistException;
	
	public Response updatenote(long id,NoteDto notedto) throws NoteDoesNotExistException;
	
	public List<NoteModel> getallnote();
	
	public List<NoteModel> getnotebytitle(String title);
	
	public Response pin_unpin(long id) throws NoteDoesNotExistException;
	
	public List<NoteModel> showpinnotes();
	
	public Response trash(long id);
	
	public List<NoteModel> showtrash();
	
	public Response deletefromtrash(long id) throws NoteDoesNotExistException;
	
	public Response archive(long id);
	
	public List<NoteModel> showArchiveNotes();
	
	
}
