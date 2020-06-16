package fr.univ.lille.fil.mbprestservice.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.univ.lille.fil.mbprestservice.entity.Support;
import fr.univ.lille.fil.mbprestservice.requestbody.SupportBody;
import fr.univ.lille.fil.mbprestservice.service.MailService;
import fr.univ.lille.fil.mbprestservice.service.SupportService;

/**
 * Classe permettant d'intéragir avec le système de support technique de l'application
 * @author Rem
 *
 */
@CrossOrigin
@RestController
public class SupportController {

	@Autowired
	private SupportService supportService;

	/**
	 * Reuqête permettant de créer un ticket de support technique
	 * @param supportBody
	 * @return L'objet support créé
	 */
	@PostMapping("/createTicket")
	public Support createTicket(@Valid @RequestBody SupportBody supportBody) {
		Support support = this.mapFromDto(supportBody);

		return supportService.createTicket(support);
	}

	/**
	 * Requête permettant de récupérer tous les tickets de support en base
	 * @return la liste des tickets de support
	 */
	@GetMapping("/getAllTickets")
	public List<Support> getAllTickets(){
		return supportService.getAllTickets();
	}

	/**
	 * Requête permettant de supprimer un ticket de support dont l'id est donnée en paramètre
	 * @param suid
	 */
	@DeleteMapping("/deleteTicket/{suid}")
	public void deleteTicket (@PathVariable("suid") int suid) {
		Support support = supportService.getTicketById(suid);
		supportService.deleteTicket(suid);
		String objectMail = "My Body Partner - Résolution du ticket #" + support.getSuid();
		String messageMail = "Bonjour " + support.getUsername() + ",\n\nNous vous informons que le ticket de support #" + support.getSuid() + " a été"
						+ " traité et est donc considéré comme résolu.\n\nNos équipes restent à votre disposition"
						+ " pour toute demande supplémentaire.\n\nL'équipe My Body Partner.";
		this.sendMail(support.getUsername(), objectMail, messageMail);
		
	}

	/**
	 * Méthode permettant de mapper l'entité support à partir d'un model objet SupportBody
	 * @param supportBody
	 * @return L'entité objet Support créé
	 */
	private Support mapFromDto(SupportBody supportBody) {
		Support support = new Support();
		support.setUsername(supportBody.getUsername());
		support.setObject(supportBody.getObject());
		support.setDescription(supportBody.getDescription());
		return support;
	}
	
	/**
	 * Méthode permettant d'envoyer un mail à l'utilisateur l'informant de la suppression d'un ticket de support
	 * @param username
	 * @param object
	 * @param message
	 */
	private void sendMail(String username, String object, String message) {
		new Thread(new MailService(username, object, message)).start();
	}

}
