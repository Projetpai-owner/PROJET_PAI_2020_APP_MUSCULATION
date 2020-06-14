package fr.univ.lille.fil.mbprestservice.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.univ.lille.fil.mbprestservice.dto.AddParticipationDTO;
import fr.univ.lille.fil.mbprestservice.entity.User;
import fr.univ.lille.fil.mbprestservice.service.ParticipationService;
import fr.univ.lille.fil.mbprestservice.service.UserService;

@RestController
@Secured(value = "ROLE_USER")
public class ParticipationController {
	
	@Autowired
	public ParticipationService pService;
	
	@Autowired
	public UserService uService;
	
	@PostMapping("/addParticipant")
	public AddParticipationDTO addParticipation(@Valid @RequestBody AddParticipationDTO body) {
		System.out.println("uid : "+body.getUid()+", aid : "+ body.getAid());
		pService.register(body.getUid(), body.getAid());
		return new AddParticipationDTO(body.getUid(), body.getAid());
	}
	
	@GetMapping("/getParticipationsForAnnonce/{aid}")
	public List<User> getParticipationsForAnnonce(@Valid @RequestBody int aid){
		List<Integer> usersToFind = pService.findUsersByParticipation(aid);
		List<User> usersFound = new ArrayList<>();
		for(Integer a: usersToFind) {
			usersFound.add(uService.findUserById(""+a));
		}
		return usersFound;
	}

}
