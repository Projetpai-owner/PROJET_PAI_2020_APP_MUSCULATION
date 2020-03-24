package fr.univ.lille.fil.mbprestservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.univ.lille.fil.mbprestservice.dao.TypeSeanceDAO;
import fr.univ.lille.fil.mbprestservice.entity.TypeSeance;

@RestController
public class TypeSeanceController {

	@Autowired
	TypeSeanceDAO typeSeanceDAO;

	@CrossOrigin
	@GetMapping("/getAllTypeSeance")
	public List<TypeSeance> getAllTypeSeance() {
		return typeSeanceDAO.findAll();
	}

}
