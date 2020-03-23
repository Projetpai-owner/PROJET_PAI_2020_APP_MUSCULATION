package fr.univ.lille.fil.mbprestservice.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.univ.lille.fil.mbprestservice.dao.UserDAO;
import fr.univ.lille.fil.mbprestservice.entity.User;

@RestController
public class UserSignUpController {
	
	@Autowired
	UserDAO userDAO;

	//save a user
	@PostMapping("/signUp/newUser")
	public User createBanni(@Valid @RequestBody User user) {
		System.out.println("here");
		return userDAO.save(user);
		
	}
	
}
