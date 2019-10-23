package com.bridgelabz.fundoo.user.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundoo.user.model.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long>{

	Optional<UserModel> findByEmail(String email);
	
	@Override
	public void deleteById(Long id) ;
	
	//@Override
	// public Optional<UserModel> findById(Long id);
	
}
