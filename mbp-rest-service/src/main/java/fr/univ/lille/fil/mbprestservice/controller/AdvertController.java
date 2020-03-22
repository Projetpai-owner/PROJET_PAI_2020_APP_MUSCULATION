package fr.univ.lille.fil.mbprestservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.univ.lille.fil.mbprestservice.dao.AdvertDAO;
import fr.univ.lille.fil.mbprestservice.entity.Advert;
import fr.univ.lille.fil.mbprestservice.requestbody.CreateAdvertBody;

@RestController
public class AdvertController {

	@Autowired
	AdvertDAO advertDao;

	@PostMapping("/createAdvert")
	public Advert createAdvert(@RequestBody CreateAdvertBody body) {
		return advertDao.save(advertDao.mapFromDto(body));
	}

}
