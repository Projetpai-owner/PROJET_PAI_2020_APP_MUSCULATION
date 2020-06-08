package fr.univ.lille.fil.mbprestservice.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.univ.lille.fil.mbprestservice.entity.Banni;
import fr.univ.lille.fil.mbprestservice.requestbody.BannirUserBody;
import fr.univ.lille.fil.mbprestservice.service.BanniService;
import fr.univ.lille.fil.mbprestservice.service.MailService;
import fr.univ.lille.fil.mbprestservice.service.UserService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@Secured(value = "ROLE_ADMIN")
public class BanniController {
	@Autowired
	private BanniService banniService;
	@Autowired
	private UserService userService;
	
		
	//save a banni
	@Transactional
	@PostMapping("/addBanni")
	public Banni createBanni(@Valid @RequestBody BannirUserBody bannirUserBody) {
		System.out.println("AO");
		Banni banni= this.mapFromDTO(bannirUserBody);
		//Supprime le user en base
		this.userService.logoutUserByUsername(banni.getEmail());
		this.userService.deleteUser(banni.getEmail());
		
		String object = "Votre compte MyBodyPartner a été banni";
		String message = "Bonjour,\n\nL'équipe MyBodyPartner vous informe que votre compte lié à l'adresse " + banni.getEmail() + 
				" a été banni.\n" + "Si vous souhaitez plus d'informations contacter le support.";
		this.sendMail(banni,object,message);
		return banniService.save(banni);
	}
	
	//list all banni
	@GetMapping("/banni")
	public List<Banni> getAllBanni(){
		return banniService.findAll();
	}
	
	//find a specific banni
	@GetMapping("/banni/{email}")
	public Optional<Banni> findById(@PathVariable String email) {
		return banniService.findById(email);
	}
	
	private Banni mapFromDTO(BannirUserBody bannirUserBody){
		Banni banni = new Banni();
		banni.setEmail(bannirUserBody.getEmail());
		return banni;
	}
	

	private void sendMail(Banni banni, String object, String message) {
		new Thread(new MailService(banni.getEmail(), object, message)).start();
	}
	
}
