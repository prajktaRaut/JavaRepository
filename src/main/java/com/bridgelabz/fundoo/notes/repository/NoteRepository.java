package com.bridgelabz.fundoo.notes.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundoo.notes.model.NoteModel;

@Repository
public interface NoteRepository extends JpaRepository<NoteModel, Long> {

		public NoteModel findByTitle(String title);
		
		
		
		public Optional<NoteModel> findById(Long id);
		

		

	
	
}
