package fr.univ.lille.fil.mbprestservice.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.univ.lille.fil.mbprestservice.dto.AddParticipationDTO;
import fr.univ.lille.fil.mbprestservice.service.ParticipationService;

@RestController
@Secured(value = "ROLE_USER")
public class ParticipationController {
	
	@Autowired
	public ParticipationService pService;
	
	@PostMapping("/addParticipant")
	public AddParticipationDTO addParticipation(@Valid @RequestBody AddParticipationDTO body) {
		System.out.println("uid : "+body.getUid()+", aid : "+ body.getAid());
		pService.register(body.getUid(), body.getAid());
		return new AddParticipationDTO(body.getUid(), body.getAid());
	}

}
