package fr.univ.lille.fil.mbprestservice.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.univ.lille.fil.mbprestservice.dto.AuthenticationResponseDTO;
import fr.univ.lille.fil.mbprestservice.entity.Salle;
import fr.univ.lille.fil.mbprestservice.entity.User;
import fr.univ.lille.fil.mbprestservice.exceptions.EmailAlreadyExistException;
import fr.univ.lille.fil.mbprestservice.requestbody.AuthenticationRequest;
import fr.univ.lille.fil.mbprestservice.requestbody.CreateUserBody;
import fr.univ.lille.fil.mbprestservice.security.JwtUtil;
import fr.univ.lille.fil.mbprestservice.service.MailService;
import fr.univ.lille.fil.mbprestservice.service.SalleService;
import fr.univ.lille.fil.mbprestservice.service.UserService;

@CrossOrigin
@RestController
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private SalleService salleService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtUtil jwtTokenUtil;


	@PostMapping("/login")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest request) throws Exception {

		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		final UserDetails userDetails = userService.loadUserByUsername(request.getUsername());
		final String jwt = jwtTokenUtil.generateToken(userDetails);
		return ResponseEntity.ok(new AuthenticationResponseDTO(jwt,""+((User)userDetails).getPid(),
				((User)userDetails).getPrenom(),userDetails.getAuthorities()));
	}
	



	// save a user
	@PostMapping("/user")
	public User createUser(@Valid @RequestBody CreateUserBody body) {
		User user = mapFromDto(body);
		if (userService.loadUserByUsername(user.getUsername())!=null) {
			throw new EmailAlreadyExistException();
		}
		this.sendMail(body);
		return userService.save(user);

	}
	
	

	// a redefinir peut etre dans une couche business ou converter
	private User mapFromDto(CreateUserBody body) {
		User user = new User();
		user.setAdresse(body.getAdresse());
		user.setBornDate(body.getBornDate());
		user.setUsername(body.getUsername());
		user.setNom(body.getNom());
		user.setPassword(body.getPassword());
		user.setPrenom(body.getPrenom());
		user.setSexe(body.getSexe());
		user.setRole(body.getRole());
		Salle salle = salleService.findById(body.getSid()).orElse(null);
		user.setSid(salle);

		return user;
	}

	private void sendMail(CreateUserBody user) {
		String object = "Confirmation Inscription MyBodyPartner";
		String message = "Bonjour " + user.getPrenom() + " " + user.getNom() + ", \n\n"
				+ "Nous vous confirmons votre inscription à l'application MyBodyPartner.";
		new Thread(new MailService(user.getUsername(), object, message)).start();
	}

}
