package com.bridgelabz.fundoo.notes.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoo.notes.dto.NoteDto;
import com.bridgelabz.fundoo.notes.exception.EmptyFieldException;
import com.bridgelabz.fundoo.notes.exception.IdDoesNotExistException;
import com.bridgelabz.fundoo.notes.exception.NoteDoesNotExistException;
import com.bridgelabz.fundoo.notes.model.NoteModel;
import com.bridgelabz.fundoo.notes.repository.NoteRepository;
import com.bridgelabz.fundoo.utility.Response;
import com.bridgelabz.fundoo.utility.TokenUtil;

@Service
@PropertySource("classpath:message.properties")
public class NoteServiceimplement implements NoteServiceInterface{

	List<NoteDto> note= new ArrayList<NoteDto>();
	
	@Autowired
	ModelMapper modelmapper;
	
	@Autowired
	NoteRepository noterepository;
	
	@Autowired
	TokenUtil tokenutil;
	
	@Autowired
	private Environment environment;
	
	@Override
	public Response addnote(NoteDto notedto) throws EmptyFieldException{
		
		if(notedto.getTitle().isEmpty() && notedto.getDescription().isEmpty())
		{
			throw new EmptyFieldException(environment.getProperty("status.note.isempty"));
		}
		else
		{
		NoteModel notemodel=modelmapper.map(notedto, NoteModel.class);
		
		notemodel.setCreated_date(LocalDateTime.now());
		notemodel.setModified_date(LocalDateTime.now());
		
		noterepository.save(notemodel);
		
		String token=tokenutil.createtoken(notemodel.getId());
		
		return new Response(HttpStatus.ACCEPTED.value(), environment.getProperty("status.note.add"), token);
		}
		
	}

	

	@Override
	public Response updatenote(long id,NoteDto notedto) throws NoteDoesNotExistException {

		NoteModel note=noterepository.findById(id).get();		
		System.out.println("Note is "+note);
		
		if(!note.equals(null))
		{
			
			note.setTitle(notedto.getTitle());
			note.setDescription(notedto.getDescription());
			note.setCreated_date(note.getCreated_date());
			note.setModified_date(LocalDateTime.now());
			
			noterepository.save(note);
			
			
			return new Response(HttpStatus.ACCEPTED.value(),environment.getProperty("status.note.update") , null);
		}
		
		throw new NoteDoesNotExistException(environment.getProperty("status.note.exist"));
		
	}



	@Override
	public Response deletenote(long id) throws IdDoesNotExistException {
	
		Optional<NoteModel> note= noterepository.findById(id);
		
		if(note.isPresent())
		{
		
		noterepository.deleteById(id);
		
		//noterepository.delete(note);
		
		return new Response(HttpStatus.ACCEPTED.value(), environment.getProperty("status.note.delete"), null);
		}
		else
		{
			throw new IdDoesNotExistException(environment.getProperty("status.note.id"));
		}
	}
	

	@Override
	public List<NoteModel> getallnote() {
		
		
		List<NoteModel> notes = noterepository.findAll();

		List<NoteModel> note = new ArrayList<NoteModel>();

		System.out.println(notes);

		for (int i = 0; i < notes.size(); i++) {
			if (!notes.get(i).isTrash() && !notes.get(i).isIspin() && !notes.get(i).isArchive()) {
				note.add(notes.get(i));
				// System.out.println(notes.get(i));
			}
		}

		return note;

	}



	@Override
	public List<NoteModel> getnotebytitle(String title) {
		
		List<NoteModel> notes=(List<NoteModel>) noterepository.findByTitle(title);
		
		return notes;
	}



	@Override
	public Response pin_unpin(long id) throws NoteDoesNotExistException{
		
		Optional<NoteModel> note= noterepository.findById(id);
		
		if(note.isPresent())
		{
			if(note.get().isIspin())
			{
				
				note.get().setIspin(false);
				noterepository.save(note.get());
				return new Response(HttpStatus.OK.value(), environment.getProperty("status.note.unpin"), null);
			}
			else 
			{
				note.get().setIspin(true);
				noterepository.save(note.get());
				return new Response(HttpStatus.OK.value(), environment.getProperty("status.note.pin"), null);
		
			}
		
		}
		
		throw new NoteDoesNotExistException(environment.getProperty("status.note.exist"));
		
	}



	@Override
	public Response trash(long id) throws NoteDoesNotExistException{
		
		Optional<NoteModel> note= noterepository.findById(id);
		
		if(note.isPresent())
		{
			if(note.get().isTrash())
			{
				
				note.get().setTrash(false);
				noterepository.save(note.get());
				return new Response(HttpStatus.OK.value(), environment.getProperty("status.note.untrash"), null);
			}
			else 
			{
				note.get().setTrash(true);
				noterepository.save(note.get());
				return new Response(HttpStatus.OK.value(), environment.getProperty("status.note.trash"), null);
		
			}
		
		}
		
		throw new NoteDoesNotExistException(environment.getProperty("status.note.exist"));	
		
		// return null;
	}



	


	@Override
	public List<NoteModel> showtrash() {
		
		List<NoteModel> notes=noterepository.findAll();
		
		List<NoteModel> trashnotes=new ArrayList<NoteModel>();
		
		for(int i=0;i<notes.size();i++)
		{
			if(notes.get(i).isTrash())
			{
				trashnotes.add(notes.get(i));
			}
		}
		
		return trashnotes;
	}



	@Override
	public Response deletefromtrash(long id) throws NoteDoesNotExistException {
		
		List<NoteModel> note= noterepository.findAll();
		
		for(int i=0;i<note.size();i++)
		{
			if(note.get(i).isTrash())
			{
				if(note.get(i).getId()==id)
				{
					noterepository.deleteById(id);
					return new Response(HttpStatus.ACCEPTED.value(), environment.getProperty("status.note.removetrashnote"), null);
				}
			}
			
		}
		throw new NoteDoesNotExistException(environment.getProperty("status.note.exist"));	
		
	}

	@Override
	public Response archive(long id) {
		
		Optional<NoteModel> note= noterepository.findById(id);
		
		if(note.isPresent())
		{
			if(note.get().isArchive())
			{
				
				note.get().setArchive(false);
				noterepository.save(note.get());
				return new Response(HttpStatus.OK.value(), environment.getProperty("status.note.istrash"), null);
			}
			else 
			{
				note.get().setArchive(true);
				noterepository.save(note.get());
				return new Response(HttpStatus.OK.value(), environment.getProperty("status.note.trash"), null);
		
			}
		}
		
		throw new NoteDoesNotExistException(environment.getProperty("status.note.exist"));	
		
	}



	@Override
	public List<NoteModel> showArchiveNotes() {
		
	List<NoteModel> notes=noterepository.findAll();
		
		List<NoteModel> archivenotes=new ArrayList<NoteModel>();
		
		for(int i=0;i<notes.size();i++)
		{
			if(notes.get(i).isArchive())
			{
				archivenotes.add(notes.get(i));
			}
		}
		
		return archivenotes;
	}



	@Override
	public List<NoteModel> showpinnotes() {
		
		List<NoteModel> notes=noterepository.findAll();
		
		List<NoteModel> pinnotes=new ArrayList<NoteModel>();
		
		for(int i=0;i<notes.size();i++)
		{
			if(notes.get(i).isIspin())
			{
				pinnotes.add(notes.get(i));
			}
		}
		
		return pinnotes;
		
		
	}


	

}
