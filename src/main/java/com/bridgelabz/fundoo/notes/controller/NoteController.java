package com.bridgelabz.fundoo.notes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoo.notes.dto.NoteDto;
import com.bridgelabz.fundoo.notes.model.NoteModel;
import com.bridgelabz.fundoo.notes.service.NoteServiceimplement;
import com.bridgelabz.fundoo.utility.Response;

@RestController
@RequestMapping("/note")
public class NoteController {
	
	@Autowired
	NoteServiceimplement noteservice;
	
	@PostMapping("/addnote")
	public ResponseEntity<Response> addnote(@RequestBody NoteDto notedto)
	{
		Response response= noteservice.addnote(notedto);
		
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	@DeleteMapping("/deletenote")
	public ResponseEntity<Response> deletenote(@RequestParam long id)
	{
		Response response=noteservice.deletenote(id);
		
		return new ResponseEntity<Response>(response, HttpStatus.OK);
		
	}
	
	@PutMapping("/updatenote/{id}")
	public ResponseEntity<Response> updatenote(@PathVariable long id,@RequestBody NoteDto notedto)
	{
		Response response=noteservice.updatenote(id,notedto);
		
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
	@GetMapping("/getnotes")
	public List<NoteModel> getallnotes()
	{
		List<NoteModel> notes=noteservice.getallnote();
		
		return notes;
	}
	
	@GetMapping("/getnote")
	public List<NoteModel> getnote(@RequestParam String title)
	{
		List<NoteModel> notes=noteservice.getnotebytitle(title);
		
		return notes;
	}
	
	@PutMapping("/pin_unpin")
	public ResponseEntity<Response> pin_unpin(@RequestParam long id)
	{
		Response response=noteservice.pin_unpin(id);
		
		return new ResponseEntity<Response>(response, HttpStatus.CONFLICT);
		
	}
	
	@GetMapping("/get_pinnote")
	public List<NoteModel> getpinnote()
	{
		List<NoteModel> notes=noteservice.showpinnotes();
		
		return notes;
	}
	
	@PutMapping("/trash")
	public ResponseEntity<Response> trash(@RequestParam long id)
	{
		Response response=noteservice.trash(id);
		
		return new ResponseEntity<Response>(response, HttpStatus.CONFLICT);
		
	}
	
	@GetMapping("/get_trashnotes")
	public List<NoteModel> gettrashnote()
	{
		List<NoteModel> notes=noteservice.showtrash();
		
		return notes;
	}
	
	@DeleteMapping("/delete_trash_note/{id}")
	public String deletefromtrash(@PathVariable long id)
	{
		 noteservice.deletefromtrash(id);
		
		return "delete from trash";
	}
	
	@PutMapping("/archive")
	public ResponseEntity<Response> archive(@RequestParam long id)
	{
		Response response=noteservice.archive(id);
		
		return new ResponseEntity<Response>(response, HttpStatus.CONFLICT);
		
	}
	
	
	@GetMapping("/get_archivenote")
	public List<NoteModel> getarchivenote()
	{
		List<NoteModel> notes=noteservice.showArchiveNotes();
		
		return notes;
	}
	
}
