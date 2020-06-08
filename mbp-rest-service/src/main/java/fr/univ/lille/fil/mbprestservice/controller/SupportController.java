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

@CrossOrigin
@RestController
public class SupportController {

	@Autowired
	private SupportService supportService;

	@PostMapping("/createTicket")
	public Support createTicket(@Valid @RequestBody SupportBody supportBody) {
		Support support = this.mapFromDto(supportBody);

		return supportService.createTicket(support);
	}

	@GetMapping("/getAllTickets")
	public List<Support> getAllTickets(){
		return supportService.getAllTickets();
	}

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

	private Support mapFromDto(SupportBody supportBody) {
		Support support = new Support();
		support.setUsername(supportBody.getUsername());
		support.setObject(supportBody.getObject());
		support.setDescription(supportBody.getDescription());
		return support;
	}
	
	private void sendMail(String username, String object, String message) {
		new Thread(new MailService(username, object, message)).start();
	}

}
