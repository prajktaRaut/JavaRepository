package com.bridgelabz.fundoo.user.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "usermodeldata")
public class UserModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "Firstname")
	private String firstname;

	@Column(name = "Lastname")
	private String lastname;

	@Column(name = "Email_id")
	private String email;


	@Column(name = "Password")
	private String password;

	@Column(name = "VerifiedUser")
	private boolean isverified = false;

	@Column(name = "RegistrationDate")
	private LocalDateTime registerdate;

	@Column(name = "UpdateDate")
	private LocalDateTime updatedate;

	public UserModel() {
		super();
	}

	public UserModel(long id, String firstname, String lastname, String email, String password, boolean isverified,
			LocalDateTime registerdate, LocalDateTime updatedate) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
		this.isverified = isverified;
		this.registerdate = registerdate;
		this.updatedate = updatedate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isIsverified() {
		return isverified;
	}

	public void setIsverified(boolean isverified) {
		this.isverified = isverified;
	}

	public LocalDateTime getRegisterdate() {
		return registerdate;
	}

	public void setRegisterdate(LocalDateTime registerdate) {
		this.registerdate = registerdate;
	}

	public LocalDateTime getUpdatedate() {
		return updatedate;
	}

	public void setUpdatedate(LocalDateTime updatedate) {
		this.updatedate = updatedate;
	}

	@Override
	public String toString() {
		return "UserModel [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", email=" + email
				+ ", password=" + password + ", isverified=" + isverified + ", registerdate=" + registerdate
				+ ", updatedate=" + updatedate + "]";
	}

	

	

	

}
