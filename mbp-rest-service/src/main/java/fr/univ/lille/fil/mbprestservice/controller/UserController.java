package fr.univ.lille.fil.mbprestservice.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.univ.lille.fil.mbprestservice.dto.AccessTokenDTO;
import fr.univ.lille.fil.mbprestservice.dto.AuthenticationResponseDTO;
import fr.univ.lille.fil.mbprestservice.entity.Salle;
import fr.univ.lille.fil.mbprestservice.entity.User;
import fr.univ.lille.fil.mbprestservice.exceptions.EmailAlreadyExistException;
import fr.univ.lille.fil.mbprestservice.exceptions.InvalidRefreshTokenException;
import fr.univ.lille.fil.mbprestservice.requestbody.AuthenticationRequest;
import fr.univ.lille.fil.mbprestservice.requestbody.CreateUserBody;
import fr.univ.lille.fil.mbprestservice.service.MailService;
import fr.univ.lille.fil.mbprestservice.service.SalleService;
import fr.univ.lille.fil.mbprestservice.service.UserService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private SalleService salleService;
	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/login")
	public AuthenticationResponseDTO createAuthenticationToken(@RequestBody AuthenticationRequest request){
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		return userService.login(request.getUsername());
	}
	
	@GetMapping("/getUser")
	public User getUser(String userId) {
		return userService.findUserById(userId);
	}

	@PostMapping("/refresh/{token}")
	public AccessTokenDTO tokenPostRefresh(@PathVariable(value="token") final String token){
		AccessTokenDTO dto= userService.refreshAccessToken(token).orElse(null);
		if(dto==null)
			throw new InvalidRefreshTokenException();
		return dto;
	}

	@DeleteMapping("/revoke/{token}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void tokenDeleteLogout(@PathVariable(value="token") final String token) {
		userService.logoutUser(token);
	}

	// save a user
	@PostMapping("/user")
	public User createUser(@Valid @RequestBody CreateUserBody body) {
		User user = mapFromDto(body);
		if (userService.loadUserByUsername(user.getUsername()) != null) {
			throw new EmailAlreadyExistException();
		}
		this.sendMail(body);
		return userService.save(user);

	}	
	//update user information
	@Transactional
	@PutMapping("/updateUser")
	public int updateUser(@Valid @RequestBody CreateUserBody body) {
		User user = mapFromDto(body);
		return userService.updateUser(user);
	}
	
	//cancel user account by deleting this user
	@Transactional
	@DeleteMapping("/cancelUserAccount")
	public int cancelUserAccount(String username) {
		return userService.cancelUserAccount(username);
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
