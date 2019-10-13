package com.demo.user.controller;


import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.user.model.User;
import com.demo.user.repository.UserRepository;

@RestController
@RequestMapping(value="/api/")
public class UserController {

	
	@Autowired
	private UserRepository userRepository;
	
	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	
	@GetMapping("/users")
	public List<User> getAllUsers() {
		log.info("getting all user details");
		return (List<User>) userRepository.findAll();
	}
	
	
	@GetMapping("/user/{emailId}")
	@Cacheable(value="users", key="#emailId")
	public User getUser( @PathVariable(value="emailId") String emailId) {
		log.info("getting single user details from DB");
		Optional<User> user = userRepository.findById(emailId);
		return user.get();
	}
	
	
	@PostMapping("/user")
	@CachePut(value="users", key="#userDetails.emailId")
	public User createUser(@RequestBody User userDetails) {
		log.info("Create new user");
		return userRepository.save(userDetails);
	}
	
	@PostMapping("/signout")
	@CacheEvict(value="users", key="#emailId")
	public boolean signOut(@RequestParam String emailId) {
		log.info("signout user " + emailId);
		return Boolean.TRUE;
	}
}
