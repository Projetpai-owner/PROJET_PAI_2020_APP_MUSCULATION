package fr.univ.lille.fil.mbprestservice.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.univ.lille.fil.mbprestservice.entity.Salle;
import fr.univ.lille.fil.mbprestservice.entity.User;
import fr.univ.lille.fil.mbprestservice.exceptions.EmailAlreadyExistException;
import fr.univ.lille.fil.mbprestservice.requestbody.CreateUserBody;
import fr.univ.lille.fil.mbprestservice.service.MailService;
import fr.univ.lille.fil.mbprestservice.service.SalleService;
import fr.univ.lille.fil.mbprestservice.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private SalleService salleService;
	@Autowired 
	private MailService mailService;
	
	//save a user
	@CrossOrigin
	@PostMapping("/user")
	public User createUser(@Valid @RequestBody CreateUserBody body) {
		User user=mapFromDto(body);
		if(userService.checkExistingEmail(user)) {
			throw new EmailAlreadyExistException();
		}
		this.sendMail(body);
		return userService.save(user);
		
	}
	
	//a redefinir peut etre dans une couche business ou converter
	private User mapFromDto(CreateUserBody body) {
		User user=new User();
		user.setAdresse(body.getAdresse());
		user.setBornDate(body.getBornDate());
		user.setUsername(body.getEmail());
		user.setNom(body.getNom());
		user.setPassword(userService.encryptPassword(body.getPassword()));
		user.setPrenom(body.getPrenom());
		user.setSexe(body.getSexe());
		Salle salle=salleService.findById(body.getSid()).orElse(null);
		user.setSid(salle);
		
		return user;
	}
	
	private void sendMail(CreateUserBody user) {
		String object = "Confirmation Inscription MyBodyPartner";
		String message = "Bonjour " + user.getPrenom() + " " + user.getNom() + ", \n\n" 
			+ "Nous vous confirmons votre inscription à l'application MyBodyPartner.";
		mailService.sendMail(user.getEmail(), object, message);
	}
	
}
