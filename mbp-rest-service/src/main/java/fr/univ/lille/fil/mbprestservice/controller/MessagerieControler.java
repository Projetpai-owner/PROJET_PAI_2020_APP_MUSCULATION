package fr.univ.lille.fil.mbprestservice.controller;

import javax.validation.Valid;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.univ.lille.fil.mbprestservice.requestbody.MessageGeneralBody;
/**
 * Controller pour gérer les opération de messagerie :
 * 	- Envoie d'un message général
 * 	- 
 * @author Clément
 *
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class MessagerieControler {
	
	@Secured(value = "ROLE_ADMIN")
	@PostMapping("/sendMessageGeneral")
	public void sendMessageGeneral(@Valid @RequestBody MessageGeneralBody messageGeneralBody){
		String objet = messageGeneralBody.getObjet();
		String message = messageGeneralBody.getMessage();
		System.out.println("OBJET : " + objet);
		System.out.println("MESSAGE : " + message);
	}
	
}
