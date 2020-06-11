package fr.univ.lille.fil.mbprestservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Classe traitant l'accès '/' à l'api REST
 * @author Anthony Bliecq
 *
 */
@RestController
public class TomcatController {

	/**
	 * Affiche un message de bienvenue
	 * @return
	 */
	@GetMapping("/")
	public String welcomeMessage() {
		return "Bienvenue sur l'api rest MyBodyPartner";
	}
}