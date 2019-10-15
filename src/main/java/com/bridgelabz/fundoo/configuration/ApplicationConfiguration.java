package com.bridgelabz.fundoo.configuration;

import javax.sql.DataSource;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ApplicationConfiguration {

	/*
	 * @Autowired DataSource datasource;
	 */
	
	@Bean
	public ModelMapper modelmapper()
	{
		ModelMapper mapper = new ModelMapper();
			
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		return mapper;
	
	}
	
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	
	/*
	 * @Autowired public void configauthentication(AuthenticationManagerBuilder
	 * auth)throws Exception {
	 * auth.jdbcAuthentication().dataSource(datasource).passwordEncoder(
	 * passwordEncoder());
	 * 
	 * }
	 */
	
	
}
