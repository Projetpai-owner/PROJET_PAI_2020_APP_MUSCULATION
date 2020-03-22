package fr.univ.lille.fil.mbprestservice.controller;

<<<<<<< HEAD
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
=======
>>>>>>> 7222fb335be2d56c333dc72ee8a9765127e03548
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.univ.lille.fil.mbprestservice.domain.Advert;
import fr.univ.lille.fil.mbprestservice.repository.CustomRepository;
import fr.univ.lille.fil.mbprestservice.requestbody.CreateAdvertBody;

@RestController
public class TomcatController {
	
	@Autowired
	CustomRepository advertRepository;

	@PostMapping("/createRequest")
	public Advert createAdvert(@RequestBody CreateAdvertBody body) {
		Advert newAdvert= new Advert();
		newAdvert.setNiveauPratique(body.getNiveauPratique());
		newAdvert.setDescription(body.getDescription());
		newAdvert.setDateSeance(body.getDateSeance());
		newAdvert.setDureeSeance(body.getDureeSeance());
		newAdvert.setNom(body.getNom());
		newAdvert.setIdSeance(null);
		advertRepository.save(newAdvert);
		return newAdvert;
	}
    @GetMapping("/")
    public String welcomeMessage() {
    	return "Bienvenue sur l'api rest MyBodyPartner";
    }
}