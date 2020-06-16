package fr.univ.lille.fil.mbprestservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.univ.lille.fil.mbprestservice.entity.Salle;
import fr.univ.lille.fil.mbprestservice.service.SalleService;

/**
 * Classe permettant d'intéragir avec les salles de sport de la base de données
 * @author Rem
 *
 */
@RestController
public class SalleController {

	@Autowired
	SalleService salleService;

	/**
	 * Requête permettant de récupérer toutes les salles de sport 
	 * @return La liste des salles en base
	 */
	@CrossOrigin
	@GetMapping("/getAllSalles")
	public List<Salle> getAllSalles() {
		return salleService.findAll();
	}

}
