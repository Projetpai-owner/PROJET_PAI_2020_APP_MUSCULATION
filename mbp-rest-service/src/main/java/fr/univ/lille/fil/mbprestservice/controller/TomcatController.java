package fr.univ.lille.fil.mbprestservice.controller;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
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

	@GetMapping("/")
	public Collection<String> sayHello() {
		return IntStream.range(0, 10).mapToObj(i -> "Hello number " + i).collect(Collectors.toList());
	}

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
}