package fr.univ.lille.fil.mbprestservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.univ.lille.fil.mbprestservice.entity.Advert;
import fr.univ.lille.fil.mbprestservice.entity.TypeSeance;
import fr.univ.lille.fil.mbprestservice.requestbody.CreateAdvertBody;
import fr.univ.lille.fil.mbprestservice.service.AdvertService;
import fr.univ.lille.fil.mbprestservice.service.TypeSeanceService;

@RestController
public class AdvertController {

	@Autowired
	AdvertService advertService;
	@Autowired
	TypeSeanceService typeSeanceService;
	
	@PostMapping("/createAdvert")
	public Advert createAdvert(@RequestBody CreateAdvertBody body) {
		return advertService.save(mapFromDto(body));
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
