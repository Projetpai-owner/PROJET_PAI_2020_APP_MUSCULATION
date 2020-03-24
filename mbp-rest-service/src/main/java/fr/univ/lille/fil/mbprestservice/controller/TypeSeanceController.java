package fr.univ.lille.fil.mbprestservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.univ.lille.fil.mbprestservice.entity.TypeSeance;
import fr.univ.lille.fil.mbprestservice.service.TypeSeanceService;

@RestController
public class TypeSeanceController {

	@Autowired
	TypeSeanceService typeSeanceService;

	@CrossOrigin
	@GetMapping("/getAllTypeSeance")
	public List<TypeSeance> getAllTypeSeance() {
		return typeSeanceService.findAll();
	}

}
