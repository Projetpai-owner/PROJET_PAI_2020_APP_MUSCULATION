package fr.univ.lille.fil.mbprestservice.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.univ.lille.fil.mbprestservice.dto.AddParticipationDTO;
import fr.univ.lille.fil.mbprestservice.entity.Advert;
import fr.univ.lille.fil.mbprestservice.entity.User;
import fr.univ.lille.fil.mbprestservice.service.AdvertService;
import fr.univ.lille.fil.mbprestservice.service.ParticipationService;
import fr.univ.lille.fil.mbprestservice.service.UserService;

/**
 * Controller qui permet d'interagir avec la participation d'une annonce
 * @author Théo
 *
 */
@RestController
@Secured(value = "ROLE_USER")
public class ParticipationController {
	
	@Autowired
	public ParticipationService pService;
	
	@Autowired
	public UserService uService;
	
	@Autowired
	public AdvertService aService;
	
	/**
	 * Requête permettant d'ajouter un participant
	 * @param body
	 * @return le participant qui a été ajouté
	 */
	@PostMapping("/addParticipant")
	public AddParticipationDTO addParticipation(@Valid @RequestBody AddParticipationDTO body) {
		pService.register(body.getUid(), body.getAid());
		return new AddParticipationDTO(body.getUid(), body.getAid());
	}
	
	/**
	 * Requête permettant de récupérer tous les participants d'une annonce
	 * @param aid
	 * @return la liste des participants d'une annonce
	 */
	@GetMapping("/getParticipationsForAnnonce/{aid}")
	public List<User> getParticipationsForAnnonce(@PathVariable(value="aid") int aid){
		List<Integer> usersToFind = pService.findUsersByParticipation(aid);
		List<User> usersFound = new ArrayList<>();
		for(Integer a: usersToFind) {
			usersFound.add(uService.findUserById(""+a));
		}
		return usersFound;
	}
	
	@GetMapping("/getParticipationsForUser/{uid}")
	public List<Advert> getParticipationsForUser(@PathVariable(value="uid") int uid){
		List<Integer> advertToFind = pService.findAdvertByParticipation(uid);
		List<Advert> advertsFound = new ArrayList<>();
		for(Integer a : advertToFind) {
			advertsFound.add(aService.findByAid(a));
		}
		return advertsFound;
	}
	
	/**
	 * Requête permettant de supprimer un participant d'une annonce
	 * @param advertId
	 * @param pid
	 */
	@Transactional
	@DeleteMapping("/deleteParticipation/{aid}/{pid}")
	public void deleteAdvert(@PathVariable("aid") int advertId, @PathVariable("pid") int pid) {
		this.pService.deleteParticipation(advertId, pid);
	}

}
