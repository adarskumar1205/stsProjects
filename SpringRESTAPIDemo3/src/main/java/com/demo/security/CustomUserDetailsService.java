package com.demo.security;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.demo.entities.User;
import com.demo.repositories.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	

	public CustomUserDetailsService() {
		super();
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		System.out.println("has reached on line 32");
	  User user =	userRepository.findByNameOrEmail(username, username)
					.orElseThrow(() -> 
						new UsernameNotFoundException(username + " not found in the databse"));
	  
	  System.out.println(user.getRoles());
	  System.out.println("has reached on line 38");
	  Set<GrantedAuthority> authorities = user.getRoles().stream()
								  					.map(role -> new SimpleGrantedAuthority(role.getName()))
								  					.collect(Collectors.toSet());		
	  System.out.println("has reached on line 42");
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
	}

}