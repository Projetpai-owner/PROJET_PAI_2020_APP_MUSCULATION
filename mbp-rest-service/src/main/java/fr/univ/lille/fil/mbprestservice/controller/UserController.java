package fr.univ.lille.fil.mbprestservice.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.univ.lille.fil.mbprestservice.entity.Advert;
import fr.univ.lille.fil.mbprestservice.entity.Salle;
import fr.univ.lille.fil.mbprestservice.entity.TypeSeance;
import fr.univ.lille.fil.mbprestservice.entity.User;
import fr.univ.lille.fil.mbprestservice.requestbody.CreateAdvertBody;
import fr.univ.lille.fil.mbprestservice.requestbody.CreateUserBody;
import fr.univ.lille.fil.mbprestservice.service.SalleService;
import fr.univ.lille.fil.mbprestservice.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private SalleService salleService;
	
	//save a user
	@CrossOrigin
	@PostMapping("/user")
	public User createUser(@Valid @RequestBody CreateUserBody body) {
		User user=mapFromDto(body);
		return userService.save(user);
		
	}
	
	//a redefinir peut etre dans une couche business ou converter
	private User mapFromDto(CreateUserBody body) {
		User user=new User();
		user.setAdresse(body.getAdresse());
		user.setBornDate(body.getBornDate());
		user.setEmail(body.getEmail());
		user.setNom(body.getNom());
		user.setPassword(body.getPassword());
		user.setPrenom(body.getPrenom());
		user.setSexe(body.getSexe());
		Salle salle=salleService.findById(body.getSid()).orElse(null);
		user.setSid(salle);
		
		return user;
	}
	
}
