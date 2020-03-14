package fr.univ.lille.fil.mbprestservice.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.univ.lille.fil.mbprestservice.dao.BanniDAO;
import fr.univ.lille.fil.mbprestservice.entity.Banni;

@RestController
public class BanniController {
	@Autowired
	BanniDAO banniDAO;

	//save a banni
	@PostMapping("/banni")
	public Banni createBanni(@Valid @RequestBody Banni banni) {
		return banniDAO.save(banni);
		
	}
	
	//list all banni
	@GetMapping("/banni")
	public List<Banni> getAllBanni(){
		return banniDAO.findAll();
	}
	
	//find a specific banni
	@GetMapping("/banni/{email}")
	public Optional<Banni> findById(@PathVariable String email) {
		return banniDAO.findById(email);
	}
}
