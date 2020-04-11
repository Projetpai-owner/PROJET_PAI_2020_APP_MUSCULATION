package fr.univ.lille.fil.mbprestservice.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.univ.lille.fil.mbprestservice.entity.Advert;
import fr.univ.lille.fil.mbprestservice.entity.TypeSeance;
import fr.univ.lille.fil.mbprestservice.requestbody.CreateAdvertBody;
import fr.univ.lille.fil.mbprestservice.service.AdvertService;
import fr.univ.lille.fil.mbprestservice.service.TypeSeanceService;

@RestController
@Secured(value = "ROLE_USER")
public class AdvertController {

	@Autowired
	AdvertService advertService;
	@Autowired
	TypeSeanceService typeSeanceService;
	
	@CrossOrigin(origins="http://localhost:4200")
	@PostMapping("/createAdvert")
	public Advert createAdvert(@Valid @RequestBody CreateAdvertBody body) {
		return advertService.save(mapFromDto(body));
	}
	
	@DeleteMapping("deleteAdvert")
	public void deleteAdvert(@RequestBody int advertId) {
		advertService.delete(advertId);
	}

	
	//a redefinir peut etre dans une couche business ou converter
	private Advert mapFromDto(CreateAdvertBody body) {
		Advert adv = new Advert();
		adv.setDescription(body.getDescription());
		adv.setDateSeance(body.getDateSeance());
		adv.setDureeSeance(body.getDureeSeance());
		adv.setNom(body.getNom());
		adv.setNiveauPratique(body.getNiveauPratique());
		TypeSeance advertTypeSeance = typeSeanceService.findById(body.getIdSeance()).orElse(null);
		adv.setIdSeance(advertTypeSeance);
		return adv;
	}
	
}
