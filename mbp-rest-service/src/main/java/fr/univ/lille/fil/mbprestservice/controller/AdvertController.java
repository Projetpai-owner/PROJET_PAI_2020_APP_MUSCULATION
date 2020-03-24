package fr.univ.lille.fil.mbprestservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.univ.lille.fil.mbprestservice.entity.Advert;
import fr.univ.lille.fil.mbprestservice.requestbody.CreateAdvertBody;
import fr.univ.lille.fil.mbprestservice.service.AdvertService;

@RestController
public class AdvertController {

	@Autowired
	AdvertService advertService;

	@PostMapping("/createAdvert")
	public Advert createAdvert(@RequestBody CreateAdvertBody body) {
		return advertService.save(advertService.mapFromDto(body));
	}

}
