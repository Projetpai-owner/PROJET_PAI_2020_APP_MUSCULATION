package fr.univ.lille.fil.mbprestservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.univ.lille.fil.mbprestservice.entity.TypeSeance;
import fr.univ.lille.fil.mbprestservice.service.TypeSeanceService;


/**
 * Controller qui permet d'interagir avec les types de séance
 * @author Théo
 *
 */
@RestController
public class TypeSeanceController {

	@Autowired
	TypeSeanceService typeSeanceService;

	/**
	 * Requête permettant de récupérer tous les types de séances
	 * @return
	 */
	@CrossOrigin(origins="http://localhost:4200/")
	@GetMapping("/getAllTypeSeance")
	public List<TypeSeance> getAllTypeSeance() {
		return typeSeanceService.findAll();
	}

}
