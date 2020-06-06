package fr.univ.lille.fil.mbprestservice.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.univ.lille.fil.mbprestservice.dto.ListAdvertItemDTO;
import fr.univ.lille.fil.mbprestservice.entity.Advert;
import fr.univ.lille.fil.mbprestservice.entity.TypeSeance;
import fr.univ.lille.fil.mbprestservice.entity.User;
import fr.univ.lille.fil.mbprestservice.requestbody.CreateAdvertBody;
import fr.univ.lille.fil.mbprestservice.requestbody.CreateUserBody;
import fr.univ.lille.fil.mbprestservice.requestbody.EditAdvertBody;
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
		return this.advertService.save(mapFromDto(body));
	}
	
	@DeleteMapping("/deleteAdvert/{aid}")
	public void deleteAdvert(@PathVariable("aid") int advertId) {
		System.out.println(advertId);
		this.advertService.delete(advertId);
	}
	
	@GetMapping("/getAllAdvertsItems")
	public List<ListAdvertItemDTO> getAllAdvertsItems(){
		return this.advertService.findAllAdvertsWithInfos();
	}
	
	@GetMapping("/getAdvertById/{id}")
	public Advert getAdvertById(@PathVariable(value="id") int id) {
		return this.advertService.findByAid(id);
	}
	
	@Transactional
	@PutMapping("/updateAdvert")
	public int updateAdvert(@Valid @RequestBody EditAdvertBody body) {
		Advert advert = mapFromDtoEdit(body);
		System.out.println(body.getAid());
		System.out.println(body.getNom());
		return advertService.updateAdvert(advert, body.getAid());
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
	
	private Advert mapFromDtoEdit(EditAdvertBody body) {
		Advert adv = new Advert();
		adv.setDescription(body.getDescription());
		adv.setDateSeance(body.getDateSeance());
		adv.setDureeSeance(body.getDureeSeance());
		adv.setNom(body.getNom());
		adv.setNiveauPratique(body.getNiveauPratique());
		TypeSeance advertTypeSeance = typeSeanceService.findById(body.getIdSeance()).orElse(null);
		adv.setIdSeance(advertTypeSeance);
		adv.setAid(body.getAid());
		return adv;
	}
	
	private CreateAdvertBody mapToDto(Advert body) {
		CreateAdvertBody adv = new CreateAdvertBody();
		adv.setDescription(body.getDescription());
		adv.setDateSeance(body.getDateSeance());
		adv.setDureeSeance(body.getDureeSeance());
		adv.setNom(body.getNom());
		adv.setNiveauPratique(body.getNiveauPratique());
		adv.setIdSeance(body.getIdSeance().getIdSeance());
		return adv;
	}
	
}
