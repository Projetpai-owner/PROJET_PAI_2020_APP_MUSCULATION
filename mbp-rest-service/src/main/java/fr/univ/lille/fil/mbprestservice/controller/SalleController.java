package fr.univ.lille.fil.mbprestservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.univ.lille.fil.mbprestservice.entity.Salle;
import fr.univ.lille.fil.mbprestservice.service.SalleService;

@RestController
public class SalleController {

	@Autowired
	SalleService salleService;

	@CrossOrigin
	@GetMapping("/getAllSalles")
	public List<Salle> getAllSalles() {
		return salleService.findAll();
	}

}
