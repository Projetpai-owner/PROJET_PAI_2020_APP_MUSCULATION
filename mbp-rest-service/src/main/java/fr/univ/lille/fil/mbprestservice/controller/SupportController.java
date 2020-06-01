package fr.univ.lille.fil.mbprestservice.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.univ.lille.fil.mbprestservice.entity.Support;
import fr.univ.lille.fil.mbprestservice.requestbody.SupportBody;
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
	
	private Support mapFromDto(SupportBody supportBody) {
		Support support = new Support();
		support.setObject(supportBody.getObject());
		support.setDescription(supportBody.getDescription());
		return support;
	}

}
