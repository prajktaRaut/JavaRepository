package com.bridgelabz.fundoo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.bridgelabz.fundoo.model.UserModel;
import java.lang.String;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel, Integer> {

	public Optional<UserModel> findByEmail(String email);
	
	public String findByPassword(String password);
	
	
	
}
