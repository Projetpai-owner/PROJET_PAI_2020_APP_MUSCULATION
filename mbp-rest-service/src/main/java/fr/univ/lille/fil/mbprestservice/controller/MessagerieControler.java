package fr.univ.lille.fil.mbprestservice.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.univ.lille.fil.mbprestservice.entity.User;
import fr.univ.lille.fil.mbprestservice.requestbody.MessageGeneralBody;
import fr.univ.lille.fil.mbprestservice.service.MailService;
import fr.univ.lille.fil.mbprestservice.service.UserService;
/**
 * Controller pour gérer les opération de messagerie :
 * 	- Envoie d'un message général
 * 	- 
 * @author Clément
 *
 */
@CrossOrigin
@RestController
public class MessagerieControler {
	
	@Autowired
	private UserService userService;
	
	/**
	 * Envoie un mail à tous les utilisateurs de l'application
	 * L'objet et le contenu du mail sont contenu dans le @RequestBody 
	 * @param messageGeneralBody @RequestBody contenant les informations du mail
	 */
	@PostMapping("/sendMessageGeneral")
	public void sendMessageGeneral(@Valid @RequestBody MessageGeneralBody messageGeneralBody){
		System.out.println("message : " + messageGeneralBody.getMessage());
		String objet = messageGeneralBody.getObjet();
		String message = messageGeneralBody.getMessage();
		
		List<User> allUsers = userService.findAll();
		for(User user : allUsers){
			new Thread(new MailService(user.getUsername(),objet,message)).start();
		}
	}
	
}
