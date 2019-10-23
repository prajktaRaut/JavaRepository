package com.bridgelabz.fundoo.notes.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "notedata")
public class NoteModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "Title")
	private String title;

	@Column(name = "Description")
	private String description;

	@Column(name = "CreatedDate")
	private LocalDateTime created_date;

	@Column(name = "ModifiedDate")
	private LocalDateTime modified_date;

	@Column(name = "Ispin")
	private boolean Ispin=false;
	
	@Column(name = "Trash")
	private boolean Trash=false;
	
	@Column(name = "Archive")
	private boolean archive=false;

	public NoteModel() {
		super();
	}

	
	public NoteModel(long id, String title, String description, LocalDateTime created_date, LocalDateTime modified_date,
			boolean ispin, boolean trash, boolean archive) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.created_date = created_date;
		this.modified_date = modified_date;
		Ispin = ispin;
		Trash = trash;
		this.archive = archive;
	}



	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getCreated_date() {
		return created_date;
	}

	public void setCreated_date(LocalDateTime created_date) {
		this.created_date = created_date;
	}

	public LocalDateTime getModified_date() {
		return modified_date;
	}

	public void setModified_date(LocalDateTime modified_date) {
		this.modified_date = modified_date;
	}

	public boolean isIspin() {
		return Ispin;
	}

	public void setIspin(boolean ispin) {
		Ispin = ispin;
	}

	public boolean isTrash() {
		return Trash;
	}

	public void setTrash(boolean trash) {
		Trash = trash;
	}


	public boolean isArchive() {
		return archive;
	}


	public void setArchive(boolean archive) {
		this.archive = archive;
	}


	@Override
	public String toString() {
		return "NoteModel [id=" + id + ", title=" + title + ", description=" + description + ", created_date="
				+ created_date + ", modified_date=" + modified_date + ", Ispin=" + Ispin + ", Trash=" + Trash
				+ ", archive=" + archive + "]";
	}

	
}
