package com.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.demo.entities.User;
import com.demo.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	public UserService() {
		super();
	}
	
	public User createUser(User user) {
	
		
		return userRepository.save(user);
		
		
 	}
	
	
	
}
